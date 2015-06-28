package hr.bpervan.mt.analyzer;

import hr.bpervan.mt.data.ItemBeaconLink;
import hr.bpervan.mt.data.Ratings;
import hr.bpervan.mt.io.Record;
import hr.bpervan.mt.main.Main;
import hr.bpervan.mt.model.User;

import java.util.*;

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

    public static final double ACC_MIN_ZAUSTAVLJANJE = 8;
    public static final double ACC_MIN_ZASTAJKIVANJE = 6;
    public static final double ACC_MIN_USPORAVANJE = 3;

    private List<ItemBeaconLink> itemBeaconLinks;
    private Ratings ratings;

    public RecordAnalyzer(Ratings ratings, List<ItemBeaconLink> itemBeaconLinks){
        this.ratings = ratings;
        this.itemBeaconLinks = itemBeaconLinks;
    }

    public void ultraNaive(List<Record> records, User user){

        int sampleCount = 0;
        for(int i = 0; i < records.size(); ++i){
            Record tempRecord = records.get(i);
            if(Math.abs(tempRecord.getAccelerometer().getX()) < 2){
                sampleCount++;
            }
        }
        if(sampleCount > 25){
            System.out.println("Zaustavljanje");
        } else if(sampleCount <= 25 && sampleCount > 10){
            System.out.println("Zastajkivanje");
        } else if(sampleCount <= 10 && sampleCount > 1 ){
            System.out.println("Usporavanje");
        } else if(sampleCount <= 1){
            System.out.println("Prolazak");
        }
    }

    public void naiveAnalytics(List<Record> records, User user){
        boolean found = false;
        int startingIndex = 0;
        for(int i = 0; i < records.size(); ++i){
            Record workingRecord = records.get(i);

            if((i + 10) < records.size()){
                for(int j = i + 1; j < (i + 11); ++j){
                    //System.out.println(i + " " +j);
                    Record tempRec = records.get(j);
                    double diff =
                            workingRecord.getAccelerometer().getX() - tempRec.getAccelerometer().getX();

                    if(diff > 6){
                        found = true;
                        startingIndex = j;
                        //System.out.println("Break: " + i + " " + j);
                        break;
                    }
                }
            }

            if(found){
                break;
            }
        }

        int lowerBound = startingIndex;
        int upperBound = startingIndex;
        int i = startingIndex - 1;
        int j = startingIndex + 1;
        Record midRecord = records.get(startingIndex);
        boolean doneAnything = true;
        while(j < records.size() && i >= 0){
            Record workingLowerRecord = records.get(i);
            Record workingHigherRecord = records.get(j);

            if(Math.abs(midRecord.getAccelerometer().getX() - workingLowerRecord.getAccelerometer().getX()) < 3){
                lowerBound--;
                doneAnything = true;
            } else {
                doneAnything = false;
            }

            if(Math.abs(midRecord.getAccelerometer().getX() - workingHigherRecord.getAccelerometer().getX()) < 3){
                upperBound++;
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
        //System.out.println(upperBound + " " + lowerBound);

        int samples = (upperBound - lowerBound);

        if(samples > 25){
            System.out.println("Zaustavljanje");
        } else if(samples <= 25 && samples > 10){
            System.out.println("Zastajkivanje");
        } else if(samples <= 10 && samples > 3 ){
            System.out.println("Usporavanje");
        } else if(samples <= 3){
            System.out.println("Prolazak");
        }
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
