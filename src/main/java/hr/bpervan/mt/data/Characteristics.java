package hr.bpervan.mt.data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Branimir on 19.6.2015..
 */
public class Characteristics {

    public double[][] data;
    private double[] df;
    private double[] idf;

    private double[][] userProfile;

    private double[][] userPreferences;

    private int numCharacteristics;
    private int numItems;

    /** Maps CharacteristicsName -> Physical index in table (column)*/
    public Map<String, Integer> characteristicNameMapper;

    /** Maps ItemId -> Physical index in table (row)*/
    public Map<Integer, Integer> itemIndexMapper;

    public Characteristics(int numCharacteristics, int numItems){
        this.numCharacteristics = numCharacteristics;
        this.numItems = numItems;

        this.df = new double[numCharacteristics];
        this.idf = new double[numCharacteristics];

        this.data = new double[numItems][numCharacteristics];

        characteristicNameMapper = new HashMap<>();
        itemIndexMapper = new HashMap<>();
    }
}
