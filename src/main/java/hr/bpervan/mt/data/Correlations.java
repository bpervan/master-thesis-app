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
public class Correlations {

    private double[][] correlTable;

    private Map<Integer, Integer> rowIndices;
    private Map<Integer, Integer> columnIndices;

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

    private double[][] cleanColumns(double[][] in){
        double[][] cleaned = new double[2][in.length];

        return cleaned;
    }

    private void calculatePearson(Ratings ratings){
        for(int i = 0; i < ratings.getNumCols(); ++i){
            for(int j = 0; j < ratings.getNumCols(); ++j){
                double[] firstCol = ratings.getTable()[i];
                double[] secondCol = ratings.getTable()[j];

                int n = 0;
                double sumX = .0;
                double sumY = .0;
                double sumXY = .0;
                double sumSqX = .0;
                double sumSqY = .0;
                for(int k = 0; k < firstCol.length; ++k){
                    if((Double.compare(firstCol[k], -100.0) != 0) && (Double.compare(secondCol[k], -100.0) != 0)){
                        n++;
                        sumXY += (firstCol[k] * secondCol[k]);
                        sumX += firstCol[k];
                        sumY += secondCol[k];

                        sumSqX += (firstCol[k] * firstCol[k]);
                        sumSqY += (secondCol[k] * secondCol[k]);
                    }
                }
                double numerator = (n * sumXY) - (sumX * sumY);
                double denominator = Math.sqrt(((n * sumSqX) - (sumX * sumX)) * ((n * sumSqY) - (sumY * sumY)));

                double correl = numerator / denominator;
                this.correlTable[i][j] = correl;
                System.out.println("i: " + i + " j: " + j + " correl: " + correl);
            }
        }
    }
}
