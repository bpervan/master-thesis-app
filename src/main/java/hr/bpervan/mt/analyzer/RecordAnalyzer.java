package hr.bpervan.mt.analyzer;

import hr.bpervan.mt.io.Record;

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

    public static final int SIGNAL_THRESHOLD_INITIAL = 10;
    public static final int SIGNAL_THRESHOLD_WORKING = 5;

    public static final int TIME_THRESHOLD = 3;

    public RecordAnalyzer(){
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
    public void analyze(List<Record> records){
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



        System.out.println(time);
    }
}
