package hr.bpervan.mt.main;

import hr.bpervan.mt.data.CorrelationTable;
import hr.bpervan.mt.data.Correlations;
import hr.bpervan.mt.data.RatingTable;
import hr.bpervan.mt.data.Ratings;
import hr.bpervan.mt.io.FileInput;
import hr.bpervan.mt.io.Record;
import hr.bpervan.mt.utils.StringUtils;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

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

        Ratings ratings = Ratings.fromCsv("ratings.csv");
        Correlations correlations = new Correlations(ratings);
        double[][] t = correlations.getTable();
        System.out.println(t[20][21]);

        System.out.println("Over and out!");
    }
}
