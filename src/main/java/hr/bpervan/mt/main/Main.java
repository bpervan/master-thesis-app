package hr.bpervan.mt.main;

import hr.bpervan.mt.data.*;
import hr.bpervan.mt.functions.Gaussian;
import hr.bpervan.mt.io.FileInput;
import hr.bpervan.mt.io.Record;
import hr.bpervan.mt.model.Item;
import hr.bpervan.mt.model.ItemBuilder;
import hr.bpervan.mt.model.User;
import hr.bpervan.mt.model.UserBuilder;
import hr.bpervan.mt.filter.*;
import hr.bpervan.mt.filter.SpaceFilter;
import hr.bpervan.mt.space.*;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;

/**
 * Created by Branimir on 2.6.2015..
 */
public class Main {

    public static Logger logger = Logger.getLogger(Main.class);

    public static final int LAYOUT_SIZE = 9;
    public static final String LAYOUT_FILE_NAME = "layout.csv";
    public static final String ITEMS_FILE_NAME = "itemswithcharacteristics.csv";
    public static final String RATINGS_FILE_NAME = "ratingstranspose.csv";

    public static void main(String[] args) throws FileNotFoundException {
        FileInput fileInput = FileInput.createInstance();
        List<Record> list = fileInput.parseFile("raw_data/dataset2/LogFile_06-06-15_17_59.txt");

        for(Record r : list){
            System.out.println(r.getRss() + " " + r.getAccelerometer() + " " + r.getMagnetometer());
        }

        /*Ratings ratings = Ratings.fromCsv(RATINGS_FILE_NAME);
        Correlations correlations = new Correlations(ratings);
        RecommendationAlgorithm algo = new UserUserFilter(ratings, correlations);

        UserBuilder testUserBuilder = UserBuilder.getInstance()
                .setUserId(89)
                .setFirstName("Testko")
                .setLastName("Testic");
        for(Integer i : ratings.getItemSet()){
            testUserBuilder.addEntryToTimeMap(i, new Gaussian());
        }
        User testUser = testUserBuilder.build();

        List<Item> itemList = Item.fromCsv(ITEMS_FILE_NAME);

        TheAlgorithm algorithm = new TheAlgorithm(
                new SpaceFilter(Graph.fromCsv("layout.csv"), itemList),
                new TimeFilter(ratings),
                new UserUserFilter(ratings, correlations),
                new ContentFilter(ratings, itemList)
        );

        List<ItemPredictionLink> result = algorithm.getTopNForUser(testUser, 100, 0);*/

        logger.info("");
        System.out.println("Over and out!");
    }
}
