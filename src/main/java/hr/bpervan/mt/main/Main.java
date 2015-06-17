package hr.bpervan.mt.main;

import hr.bpervan.mt.data.*;
import hr.bpervan.mt.io.FileInput;
import hr.bpervan.mt.io.Record;
import hr.bpervan.mt.utils.StringUtils;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Branimir on 2.6.2015..
 */
public class Main {
    public static void main(String[] args){
        /*FileInput fileInput = FileInput.createInstance();
        List<Record> list = fileInput.parseFile("raw_data/dataset1/LogFile_06-06-15_17_38.txt");

        for(Record r : list){
            System.out.println(r);
        }*/

        Ratings ratings = Ratings.fromCsv("ratingstranspose.csv");

        Correlations correlations = new Correlations(ratings);
        //List<UserCorrelationLink> list = correlations.getNeighbourhood(3867, 6);

        System.out.println(ratings.getAverageGivenToItem(36658));
        System.out.println(ratings.getAverageGivenByUser(3712));


        System.out.println("Over and out!");
    }
}
