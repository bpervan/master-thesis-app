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
import hr.bpervan.mt.recommender.*;
import hr.bpervan.mt.recommender.SpaceFilter;
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

    public static final int LAYOUT_SIZE = 9;

    public static void main(String[] args) throws FileNotFoundException {
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

        TheAlgorithm algorithm = new TheAlgorithm(
                new SpaceFilter(Graph.fromCsv("layout.csv")),
                new TimeFilter(ratings),
                new UserUser(ratings, correlations)
        );

        List<ItemPredictionLink> result = algorithm.getTopNForUser(testUser, 20, 0);
        result.forEach(ipl -> logger.info(ipl));

        logger.info("");
        System.out.println("Over and out!");
    }



    public static void Load() throws FileNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("locationsunfiltered.csv");

        Random random = new Random();
        int i = 0;
        PrintWriter printWriter = new PrintWriter(new File("locationsfiltered.csv"));
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))){
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                String[] parts = line.split(":");
                printWriter.println(parts[0] + ";" + "Item" + parts[0] + ";" + random.nextInt(LAYOUT_SIZE));
                i++;
            }
            printWriter.close();
            bufferedReader.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
