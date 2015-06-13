package hr.bpervan.mt.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Branimir on 5.6.2015..
 */
public class FileInput {

    private FileInput(){}
    public static FileInput createInstance(){ return new FileInput(); }

    public List<Record> parseFile(String resourcesPath){
        List<Record> returnList = new ArrayList<>();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(resourcesPath);

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))){
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                returnList.add(this.parseLine(line));
            }

            bufferedReader.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        return returnList;
    }

    private Record parseLine(String line){
        /** 0: Timestamp, 1: id and rss, 2: accelerometer, 3: magnetometer*/
        String[] parts = line.split("#");
        String[] idAndRss = parts[1].split("\\$");
        return new Record(
            Timestamp.fromString(parts[0]),
            idAndRss[0],
            Integer.parseInt(idAndRss[1]),
            Coordinate3D.fromString(parts[2]),
            Coordinate3D.fromString(parts[3])
        );
    }
}
