package hr.bpervan.mt.analyzer;

import hr.bpervan.mt.data.ItemBeaconLink;
import hr.bpervan.mt.data.Ratings;
import hr.bpervan.mt.io.Record;
import hr.bpervan.mt.main.Main;
import hr.bpervan.mt.model.User;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Branimir on 21.6.2015..
 * Reinforcement system: +1 for buying, 0 for pass by, 0.5 for stopping by
 * Pro futuro: function: timePassedInStoppingBy -> reinforcement
 */
public class RecordAnalyzer {

    public static final int SCANNING_FRAME = 5;

    public static final int SIGNAL_THRESHOLD_INITIAL = 15;
    public static final int SIGNAL_THRESHOLD_WORKING = 5;

    public static final int MIN_FOR_REINFORCEMENT = 6;
    public static final int MIN_FOR_STOPBY = 8;

    public static final double ACC_MIN_STOP = 1.5;
    public static final double ACC_MIN_STOPBY = 3.0;
    public static final double ACC_MIN_SLOWING = 4.5;

    private List<ItemBeaconLink> itemBeaconLinks;
    private Ratings ratings;

    public RecordAnalyzer(Ratings ratings, List<ItemBeaconLink> itemBeaconLinks){
        this.ratings = ratings;
        this.itemBeaconLinks = itemBeaconLinks;
    }

    /**
     * Pretpostavke:
     * 1. Postoje thresholdi za proglašavanje zaustavljanja i zastajkivanja, sve ostalo se tretira kao prolazak
     * 2. Razina reinforcementa ovisi o duljini trajanja zaustavljanja i zastajkivanja
     * 3. Pod zaustavljanje i zastajkivanje raèunat æe se maksimalan signal + svi oni signali koji su unutar
     *    neke granice od maksimalnog signala
     *
     * Algoritam:
     * 1. Naði maksimum (lambda)
     * 2. Kreni unazad, zapis po zapis od maksimuma i naði poèetak okvira koji raèunamo kao interes za taj predmet
     * 3. Kreni naprijed, naði kraj okvira koji raèunamo kao interes.
     * 4. Izraèunaj trajanje i klasificiraj kao pass by, zastajkivanje ili zaustavljanje.
     * 5. Dodaj reinforcement za usera
     * */
    public void analyze(List<Record> records, User user){
        final int maxRss = records.stream().max(Comparator.comparing(record -> record.getRss())).get().getRss();

        Record maxRssRecord = records.stream().filter(p -> p.getRss() == maxRss).findFirst().get();

        int lowerBound = records.indexOf(maxRssRecord);
        int highBound = lowerBound;

        int i = lowerBound - 1;
        int j = highBound + 1;
        boolean doneAnything = true;
        while(j < records.size() && i >= 0){
            Record workingLowerRecord = records.get(i);
            Record workingHigherRecord = records.get(j);

            if(maxRssRecord.getRss() - workingLowerRecord.getRss() < SIGNAL_THRESHOLD_INITIAL){
                lowerBound--;
                doneAnything = true;
            } else {
                doneAnything = false;
            }

            if(maxRssRecord.getRss() - workingHigherRecord.getRss() < SIGNAL_THRESHOLD_INITIAL){
                highBound++;
                doneAnything = true;
            } else {
                doneAnything = false;
            }

            i--;
            j++;

            if(!doneAnything){
                break;
            }
        }

        List<Record> helperList = records.subList(lowerBound, highBound);
        Set<Integer> distinctTimes = new HashSet<>();
        for(int k = lowerBound; k <= highBound; ++k){
            distinctTimes.add(records.get(k).getTimestamp().getSeconds());
        }
        int time = distinctTimes.size();
        int itemId = itemBeaconLinks
                .stream()
                .filter(p -> p.getBeaconId().equals(maxRssRecord.getBeaconId()))
                .findFirst()
                .get()
                .getItemId();

        if(time >= MIN_FOR_STOPBY){
            /** Zaustavljanje reinforcement*/
            ratings.reinforce(user.getUserId(), itemId, 0.5);
            //System.out.println("Reinforcement Stopby: " + ratings.getValue(user.getUserId(), itemId) + " ");
        } else if(time >= MIN_FOR_REINFORCEMENT && time < MIN_FOR_STOPBY){
            /** Zastajkivanje reinforcement */
            ratings.reinforce(user.getUserId(), itemId, 0.3);
            //System.out.println("Reinforcement slow: " + ratings.getValue(user.getUserId(), itemId) + " ");
        } else {
            /** Prolazak */
            //System.out.println("No reinforcement");
        }
        //System.out.println(maxRssRecord.getRss() + " " + time);
    }

    public void analyzeByAcc(List<Record> records, User user){
        /** Faza 1. Skeniraj zapise */
        for(int i = 0; i < records.size(); ++i){
            double accX = records.get(i).getAccelerometer().getX();
            if(accX <= ACC_MIN_SLOWING && accX > ACC_MIN_STOPBY){
                /** Usporavanje*/

            } else if(accX <= ACC_MIN_STOPBY && accX > ACC_MIN_STOP){
                /** Zastajkivanje*/

            } else if(accX <= ACC_MIN_STOP){
                /** Zaustavljanje */

            } else {
                /** Prolazak*/

            }
        }
    }

    public void analyzeByAccSimple(List<Record> records, User user){
        final double minAcc = records.stream().min(Comparator.comparing(record -> record.getAccelerometer().getX())).get().getAccelerometer().getX();

        Record minAccRecord = records.stream().filter(p -> p.getAccelerometer().getX() == minAcc).findFirst().get();

        int lowerBound = records.indexOf(minAccRecord);
        int highBound = lowerBound;

        int i = lowerBound - 1;
        int j = highBound + 1;
        boolean doneAnything = true;
        while(j < records.size() && i >= 0){
            Record workingLowerRecord = records.get(i);
            Record workingHigherRecord = records.get(j);

            if(minAccRecord.getAccelerometer().getX() - workingLowerRecord.getAccelerometer().getX() < 1.0){
                lowerBound--;
                doneAnything = true;
            } else {
                doneAnything = false;
            }

            if(minAccRecord.getAccelerometer().getX() - workingHigherRecord.getAccelerometer().getX() < 1.0){
                highBound++;
                doneAnything = true;
            } else {
                doneAnything = false;
            }

            i--;
            j++;

            if(!doneAnything){
                break;
            }
        }

        List<Record> helperList = records.subList(lowerBound, highBound);
        Set<Integer> distinctTimes = new HashSet<>();
        for(int k = lowerBound; k <= highBound; ++k){
            distinctTimes.add(records.get(k).getTimestamp().getSeconds());
        }
        int time = distinctTimes.size();
        int itemId = itemBeaconLinks
                .stream()
                .filter(p -> p.getBeaconId().equals(minAccRecord.getBeaconId()))
                .findFirst()
                .get()
                .getItemId();

        System.out.println(time + " " + minAccRecord.getAccelerometer().getX());

        if(time >= MIN_FOR_STOPBY){
            /** Zaustavljanje reinforcement*/
            ratings.reinforce(user.getUserId(), itemId, 0.5);
            System.out.println("Reinforcement Stopby: " + ratings.getValue(user.getUserId(), itemId) + " ");
        } else if(time >= MIN_FOR_REINFORCEMENT && time < MIN_FOR_STOPBY){
            /** Zastajkivanje reinforcement */
            ratings.reinforce(user.getUserId(), itemId, 0.3);
            System.out.println("Reinforcement slow: " + ratings.getValue(user.getUserId(), itemId) + " ");
        } else {
            /** Prolazak */
            System.out.println("No reinforcement");
        }
        //System.out.println(maxRssRecord.getRss() + " " + time);
    }

    private class MovingData{
        private int duration;
        private int startingSecond;
        private MovingType type;

        public MovingData(int duration, int startingSecond, MovingType type){
            this.duration = duration;
            this.startingSecond = startingSecond;
            this.type = type;
        }
    }

    private enum MovingType{
        ZAUSTAVLJANJE,
        ZASTAJKIVANJE,
        USPORAVANJE,
        KRETANJE
    }
}
