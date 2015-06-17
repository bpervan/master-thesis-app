package hr.bpervan.mt.data;

import hr.bpervan.mt.model.User;

import java.util.*;

/**
 * Created by Branimir on 16.6.2015..
 */
public class Correlations {

    private double[][] correlTable;

    private Map<Integer, Integer> rowIndices;
    private Map<Integer, Integer> columnIndices;
    private double[][] in;

    private Correlations(){
        this.rowIndices = new HashMap<>();
        this.columnIndices = new HashMap<>();
    }

    public Correlations(Ratings ratings){
        correlTable = new double[ratings.getNumCols()][ratings.getNumCols()];
        this.rowIndices = new HashMap<>();
        this.columnIndices = new HashMap<>();
        this.calculatePearson(ratings);
    }

    public double[][] getTable(){
        return correlTable;
    }


    public List<UserCorrelationLink> getNeighbourhood(int user, int n){
        List<UserCorrelationLink> list = new ArrayList<>();
        for(Map.Entry<Integer, Integer> e : this.rowIndices.entrySet()){
            list.add(new UserCorrelationLink(
                    e.getKey(),
                    this.getCorrelation(user, e.getKey())
            ));
        }
        Collections.sort(list, Collections.reverseOrder());

        return list.subList(1, n + 1);
    }

    public double getCorrelation(Integer user1, Integer user2){
        return this.correlTable[rowIndices.get(user1)][columnIndices.get(user2)];
    }

    private void calculatePearson(Ratings ratings){
        for(int i = 0; i < ratings.getNumRows(); ++i){
            for(int j = 0; j < ratings.getNumRows(); ++j){

                double[] firstRow = ratings.getTable()[i];
                double[] secondRow = ratings.getTable()[j];

                //System.out.println(ratings.getRowKeyByValue(i) + " " + ratings.getColumnKeyByValue(j));
                this.rowIndices.put(ratings.getRowKeyByValue(i), i);
                this.columnIndices.put(ratings.getRowKeyByValue(j), j);

                int n = 0;
                double sumX = .0;
                double sumY = .0;
                double sumXY = .0;
                double sumSqX = .0;
                double sumSqY = .0;
                for(int k = 0; k < firstRow.length; ++k){
                    if((Double.compare(firstRow[k], -100.0) != 0) && (Double.compare(secondRow[k], -100.0) != 0)){
                        n++;
                        sumXY += (firstRow[k] * secondRow[k]);
                        sumX += firstRow[k];
                        sumY += secondRow[k];

                        sumSqX += (firstRow[k] * firstRow[k]);
                        sumSqY += (secondRow[k] * secondRow[k]);
                    }
                }
                double numerator = (n * sumXY) - (sumX * sumY);
                double denominator = Math.sqrt(((n * sumSqX) - (sumX * sumX)) * ((n * sumSqY) - (sumY * sumY)));

                double correl = numerator / denominator;
                this.correlTable[i][j] = correl;
                //System.out.println("i: " + this.columnIndices.get(i) + " j: " + this.rowIndices.get(j) + " correl: " + correl);
                //System.out.println("i: " + i + " j: " + j + " correl: " + correl);
            }
        }
    }
}
