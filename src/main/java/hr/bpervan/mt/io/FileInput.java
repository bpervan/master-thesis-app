package hr.bpervan.mt.io;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Branimir on 5.6.2015..
 */
public class FileInput {

    public FileInput(){}

    
    public List<Record> parseFile(String absolutePath){
        List<Record> returnList = new ArrayList<>();
        File textFile = new File(absolutePath);
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile))){
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                returnList.addAll(this.parseLine(line));
            }

            bufferedReader.close();
        } catch (Exception e){

        }

        return returnList;
    }

    private List<Record> parseLine(String line){
        List<Record> returnList = new ArrayList<>();
        String[] parts = line.split(";");

        String timestamp = parts[0];

        String[] keyValuePairs = parts[1].split(",");

        for(String keyValuePair : keyValuePairs){
            String[] keyValue = keyValuePair.split(":");
            returnList.add(
                    new Record(
                            timestamp,
                            Double.parseDouble(keyValue[1]),
                            Integer.parseInt(keyValue[0]))
            );
        }

        return returnList;
    }
}
