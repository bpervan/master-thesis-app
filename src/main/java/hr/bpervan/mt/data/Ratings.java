package hr.bpervan.mt.data;

import hr.bpervan.mt.utils.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Branimir on 16.6.2015..
 */
public class Ratings {
    private double[][] ratingTable;

    private double[] averageGivenByUser;
    private double[] averageGivenToItem;

    /** These Maps translate user or item ids to physical keys in double[][] */
    private Map<Integer, Integer> rowIndices;
    private Map<Integer, Integer> columnIndices;

    private Ratings(){
        this.rowIndices = new HashMap<>();
        this.columnIndices = new HashMap<>();
    }

    public Set<Integer> getItemSet(){
        return columnIndices.keySet();
    }

    public Set<Integer> getUserSet(){
        return rowIndices.keySet();
    }

    public double getAverageGivenByUser(int userId) {
        return averageGivenByUser[rowIndices.get(userId)];
    }

    public double getAverageGivenToItem(int itemId) {
        return averageGivenToItem[columnIndices.get(itemId)];
    }

    private void calculateAverageGivenByUser(){
        double positiveCount;
        double sum;
        this.averageGivenByUser = new double[ratingTable.length];
        for (int i = 0; i < ratingTable.length; i++) {
            double[] row = ratingTable[i];
            positiveCount = 0;
            sum = 0;

            for(double d : row){
                if(Double.compare(d, -100.0) != 0){
                    positiveCount++;
                    sum += d;
                }
            }
            //System.out.println(sum + " " + positiveCount);
            this.averageGivenByUser[i] = (sum / positiveCount);
        }
    }

    private void calculateAverageGivenToItem(){
        double positiveCount;
        double sum;
        this.averageGivenToItem = new double[ratingTable[0].length];
        for (int i = 0; i < ratingTable[0].length; i++) {
            positiveCount = 0;
            sum = 0;
            for (int j = 0; j < ratingTable.length; j++) {
                if(Double.compare(ratingTable[j][i], -100.0) != 0){
                    positiveCount++;
                    sum += ratingTable[j][i];
                }
                System.out.print(ratingTable[j][i] + " ");
            }
            System.out.println();
            this.averageGivenToItem[i] = (sum / positiveCount);
        }
    }

    public Integer getRowKeyByValue(Integer in){
        for(Map.Entry<Integer, Integer> e : this.rowIndices.entrySet()){
            if(e.getValue().equals(in)){
                return e.getKey();
            }
        }
        return null;
    }

    public Integer getColumnKeyByValue(Integer in){
        for(Map.Entry<Integer, Integer> e : this.columnIndices.entrySet()){
            if(e.getValue().equals(in)){
                return e.getKey();
            }
        }
        return null;
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
                String[] parts = columnIndices[i].split(":");
                ratings.columnIndices.put(Integer.parseInt(parts[0]), i - 1);
            }

            int rowNum = StringUtils.countLines(classLoader.getResource(relativePath).getFile()) - 1;
            ratings.ratingTable = new double[rowNum][columnIndices.length - 1];
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
                        ratings.ratingTable[rowKeyCounter][i - 1] = -100.0;
                    } else {
                        ratings.ratingTable[rowKeyCounter][i - 1] = Double.parseDouble(parts[i]);
                    }
                }

                rowKeyCounter++;

            }
            bufferedReader.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        ratings.calculateAverageGivenByUser();
        ratings.calculateAverageGivenToItem();

        return ratings;
    }
}
