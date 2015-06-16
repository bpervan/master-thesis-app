package hr.bpervan.mt.main;

import hr.bpervan.mt.data.CorrelationTable;
import hr.bpervan.mt.data.RatingTable;
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


        RatingTable ratingTable = RatingTable.fromCsv("ratings.csv");


        CorrelationTable correlationTable = new CorrelationTable(ratingTable);

        PearsonsCorrelation correlation = new PearsonsCorrelation();

        System.out.println(ratingTable.getTable().get(11,5136));
        System.out.println("Over and out!");
    }
}
