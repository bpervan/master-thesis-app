package hr.bpervan.mt.main;

import hr.bpervan.mt.io.FileInput;
import hr.bpervan.mt.io.Record;

import java.util.List;

/**
 * Created by Branimir on 2.6.2015..
 */
public class Main {
    public static void main(String[] args){
        FileInput fileInput = FileInput.createInstance();
        List<Record> list = fileInput.parseFile("raw_data/dataset1/LogFile_06-06-15_17_38.txt");

        for(Record r : list){
            System.out.println(r);
        }

        System.out.println("Over and out!");
    }
}
