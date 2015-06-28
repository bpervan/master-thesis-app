package hr.bpervan.mt.main;

import hr.bpervan.mt.analyzer.RecordAnalyzer;
import hr.bpervan.mt.data.*;
import hr.bpervan.mt.filter.settings.FilterMixType;
import hr.bpervan.mt.filter.settings.FilterSettings;
import hr.bpervan.mt.filter.settings.FilterSettingsBuilder;
import hr.bpervan.mt.filter.time.TimeFilterImplementation;
import hr.bpervan.mt.functions.Gaussian;
import hr.bpervan.mt.io.FileInput;
import hr.bpervan.mt.io.Record;
import hr.bpervan.mt.model.Item;
import hr.bpervan.mt.model.User;
import hr.bpervan.mt.model.UserBuilder;
import hr.bpervan.mt.filter.*;
import hr.bpervan.mt.filter.space.SpaceFilterImplementation;
import hr.bpervan.mt.space.*;
import hr.bpervan.mt.analyzer.TestFilesHelper;
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
    public static final String BEACONS_FILE_NAME = "itemsbeacons.csv";

    public static void main(String[] args) throws FileNotFoundException {
        FileInput fileInput = FileInput.createInstance();
        //List<Record> list = fileInput.parseFile("raw_data/dataset7/LogFile_06-06-15_18_26.txt");

        Ratings ratings = Ratings.fromCsv(RATINGS_FILE_NAME);
        Correlations correlations = new Correlations(ratings);
        List<ItemBeaconLink> itemsBeacons = ItemBeaconLink.fromCsv(BEACONS_FILE_NAME);


        /*list
                .forEach(r ->
                        System.out.println(
                                r.getTimestamp().getSeconds() + " "
                                        + r.getRss() + " "
                                        + r.getAccelerometer() + " "
                                        + r.getMagnetometer()));*/

        Random r = new Random();

        UserBuilder testUserBuilder = UserBuilder.getInstance()
                .setUserId(89)
                .setFirstName("Testko")
                .setLastName("Testic");
        for(Integer i : ratings.getItemSet()){
            double randomA = 0.2 + (0.25 - 0.2) * r.nextDouble();
            double randomB = 2.2 + (2.7 - 2.2) * r.nextDouble();
            double randomC = 1.8 + (2.2 - 1.8) * r.nextDouble();

            testUserBuilder.addEntryToTimeMap(i, new Gaussian(randomA, randomB, randomC));
        }
        User testUser = testUserBuilder.build();

        List<Item> itemList = Item.fromCsv(ITEMS_FILE_NAME);

        TheAlgorithm algorithm = new TheAlgorithm(
                new SpaceFilterImplementation(Graph.fromCsv(LAYOUT_FILE_NAME), itemList),
                new TimeFilterImplementation(ratings),
                new UserUserFilter(ratings, correlations),
                new ContentFilter(ratings, itemList),
                FilterMixType.BOTH_FILTERS,
                0.5
        );

        List<ItemPredictionLink> result = algorithm.getTopNForUser(testUser, 100, 0);
        /*System.out.println("SUM:----------------");
        result.forEach(i -> System.out.println(i));
        System.out.println("END SUM:----------------");*/
        RecordAnalyzer recordAnalyzer = new RecordAnalyzer(ratings, itemsBeacons);
        for(String fileName : TestFilesHelper.filesList()){

            System.out.println("File: " + fileName);
            List<Record> list = fileInput.parseFile(fileName);
            //list.forEach(i -> System.out.println(i.getRss() + " " + i.getAccelerometer()));
            list.forEach(i -> System.out.print(i.getAccelerometer().getX() + " "));
            System.out.println();
            list.forEach(i -> System.out.print(i.getAccelerometer().getY() + " "));
            System.out.println();
            list.forEach(i -> System.out.print(i.getAccelerometer().getZ() + " "));
            System.out.println();
            list.forEach(i -> System.out.print(i.getAccelerometer().getAbs() + " "));
            System.out.println();
            System.out.println(list.size());
            recordAnalyzer.naiveAnalytics(list, testUser);

            System.out.println();
            //System.out.print(ratings.getValue(testUser.getUserId(), itemList.get(0).getItemId()));
            //System.out.println();
        }

        Graph layout = Graph.fromCsv(LAYOUT_FILE_NAME);

        FilterSettingsBuilder builder = FilterSettingsBuilder.getInstance();

        logger.info("");
        System.out.println("Over and out!");
    }
}
