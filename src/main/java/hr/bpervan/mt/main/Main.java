package hr.bpervan.mt.main;

import hr.bpervan.mt.data.*;
import hr.bpervan.mt.functions.Function;
import hr.bpervan.mt.functions.Gaussian;
import hr.bpervan.mt.io.FileInput;
import hr.bpervan.mt.io.Record;
import hr.bpervan.mt.model.Item;
import hr.bpervan.mt.model.ItemBuilder;
import hr.bpervan.mt.model.User;
import hr.bpervan.mt.model.UserBuilder;
import hr.bpervan.mt.recommender.RecommendationAlgorithm;
import hr.bpervan.mt.recommender.SpaceFilter;
import hr.bpervan.mt.recommender.TimeFilter;
import hr.bpervan.mt.recommender.UserUser;
import hr.bpervan.mt.space.*;
import hr.bpervan.mt.utils.StringUtils;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;

/**
 * Created by Branimir on 2.6.2015..
 */
public class Main {

    public static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args){
        /*FileInput fileInput = FileInput.createInstance();
        List<Record> list = fileInput.parseFile("raw_data/dataset1/LogFile_06-06-15_17_38.txt");

        for(Record r : list){
            System.out.println(r);
        }*/

        Ratings ratings = Ratings.fromCsv("ratingstranspose.csv");
        Correlations correlations = new Correlations(ratings);
        RecommendationAlgorithm algo = new UserUser(ratings, correlations);

        UserBuilder testUserBuilder = UserBuilder.getInstance()
                .setUserId(89)
                .setFirstName("Testko")
                .setLastName("Testic");
        for(Integer i : ratings.getItemSet()){
            testUserBuilder.addEntryToTimeMap(i, new Gaussian());
        }
        User testUser = testUserBuilder.build();

        Item testItem = ItemBuilder.getInstance()
                .setItemId(12)
                .setItemName("Star Wars")
                .build();

        List<ItemPredictionLink> resultList = algo.getTopNForUser(testUser, 5);
        //resultList.forEach(itl -> System.out.println(itl));

        RecommendationAlgorithm algo1 = new TimeFilter(ratings);
        List<ItemPredictionLink> timeResult = algo1.getTopNForUser(testUser, 5);
        //timeResult.forEach(it -> System.out.println(it));

        SpaceFilter algo2 = new SpaceFilter(Graph.fromCsv("layout.csv"));
        List<ItemPredictionLink> spaceResult = algo2.getTopNForUser(null, 5, 0);
        spaceResult.stream().forEach(i -> System.out.println(i));

        System.out.println("Over and out!");
    }
}
