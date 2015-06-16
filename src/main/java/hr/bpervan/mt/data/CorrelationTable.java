package hr.bpervan.mt.data;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Branimir on 15.6.2015..
 */
public class CorrelationTable {

    private Table<Integer, Integer, Double> table;

    public CorrelationTable(RatingTable in){
        table = HashBasedTable.create();
        this.calculatePearson(in.getTable());
    }

    public Table<Integer, Integer, Double> getTable() {
        return table;
    }

    public void setTable(Table<Integer, Integer, Double> table) {
        this.table = table;
    }

    public void calculatePearson(Table<Integer, Integer, Double> ratingTable){
        Iterator<Integer> first = ratingTable.columnKeySet().iterator();
        Iterator<Integer> second = ratingTable.columnKeySet().iterator();

        for(Integer rowKey1 : ratingTable.columnKeySet()){
            for(Integer rowKey2 : ratingTable.columnKeySet()){

                Map<Integer, Double> firstColumn = ratingTable.column(rowKey1);
                Map<Integer, Double> secondColumn = ratingTable.column(rowKey2);

                double xAverage = .0;
                double yAverage = .0;
                for(Map.Entry<Integer, Double> entry1 : firstColumn.entrySet()){
                    //if(entry1.getValue() != -1.0){
                        xAverage += entry1.getValue();
                    //}
                }
                for(Map.Entry<Integer, Double> entry2 : secondColumn.entrySet()){
                    //if(entry2.getValue() != -1.0){
                        yAverage += entry2.getValue();
                    //}
                }

                double numeratorSum = .0;
                for(Map.Entry<Integer, Double> e : firstColumn.entrySet()){
                    double xTemp = .0;
                    double yTemp = .0;

                    if((e.getValue() != .0) && (secondColumn.get(e.getKey()) != .0)){
                        xTemp += (e.getValue() - xAverage);
                        yTemp += (secondColumn.get(e.getKey()) - yAverage);
                    }
                    numeratorSum += (xTemp * yTemp);
                }

                double xSqTemp = .0;
                for(Map.Entry<Integer, Double> e1 : firstColumn.entrySet()){
                    double xTemp = .0;
                    //if(e1.getValue() != -1.0){
                        xTemp += (e1.getValue() - xAverage);
                    //} else {
                        //xTemp += (-xAverage);
                    //}
                    xSqTemp += (xTemp * xTemp);
                }

                double ySqTemp = .0;
                for(Map.Entry<Integer, Double> e2 : secondColumn.entrySet()){
                    double yTemp = .0;
                    //if(e2.getValue() != -1.0){
                        yTemp += (e2.getValue() - yAverage);
                    //} else {
                        //yTemp += (-yAverage);
                    //}
                    ySqTemp += (yTemp * yTemp);
                }

                double correl  = numeratorSum / Math.sqrt(xSqTemp * ySqTemp);

                //System.out.println("Row1: " + rowKey1 + " Row2: " + rowKey2 + " Correl: " + correl);
                table.put(rowKey1, rowKey2, correl);
            }
        }
    }
}
