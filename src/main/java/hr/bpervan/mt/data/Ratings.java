package hr.bpervan.mt.data;

import hr.bpervan.mt.utils.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Branimir on 16.6.2015..
 */
public class Ratings {
    private double[][] ratingTable;

    /** (Virtualni kljuè, Fizièki kljuè)*/
    private Map<Integer, Integer> rowIndices;
    private Map<Integer, Integer> columnIndices;

    private Ratings(){
        this.rowIndices = new HashMap<>();
        this.columnIndices = new HashMap<>();
    }

    public double[][] getTable(){
        return this.ratingTable;
    }

    public double getValue(Integer rowKey, Integer columnKey){
        return this.ratingTable[rowIndices.get(rowKey)][columnIndices.get(columnKey)];
    }

    public int getNumRows(){
        return ratingTable.length;
    }

    public int getNumCols(){
        return ratingTable[0].length;
    }

    public static Ratings fromCsv(String relativePath){
        Ratings ratings = new Ratings();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(relativePath);

        StringBuilder sb;

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line = null;

            line = bufferedReader.readLine();
            String[] columnIndices = line.split(";");
            for(int i = 1; i < columnIndices.length; ++i){
                ratings.columnIndices.put(Integer.parseInt(columnIndices[i]), i);
            }

            int rowNum = StringUtils.countLines(classLoader.getResource(relativePath).getFile()) - 1;
            ratings.ratingTable = new double[rowNum][columnIndices.length];
            int rowKeyCounter = 0;
            while ((line = bufferedReader.readLine()) != null) {
                if(line.charAt(line.length() - 1) == ';'){
                    sb = new StringBuilder(line.subSequence(0, line.length() - 1));
                    sb.append(";-100.0");
                    line = sb.toString();
                }
                String[] parts = line.split(";");

                int rowKey = Integer.parseInt(parts[0]);
                ratings.rowIndices.put(rowKey, rowKeyCounter);

                for(int i = 1; i < parts.length; ++i){
                    if(parts[i].isEmpty()){
                        ratings.ratingTable[rowKeyCounter][i] = -100.0;
                    } else {
                        ratings.ratingTable[rowKeyCounter][i] = Double.parseDouble(parts[i]);
                    }
                }

                rowKeyCounter++;

            }
            bufferedReader.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        return ratings;
    }
}
