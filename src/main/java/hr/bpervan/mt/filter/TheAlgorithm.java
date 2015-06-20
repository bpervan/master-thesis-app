package hr.bpervan.mt.filter;

import hr.bpervan.mt.data.Correlations;
import hr.bpervan.mt.data.ItemPredictionLink;
import hr.bpervan.mt.data.Ratings;
import hr.bpervan.mt.model.Item;
import hr.bpervan.mt.model.User;
import hr.bpervan.mt.space.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Branimir on 18.6.2015..
 */
public class TheAlgorithm implements RecommendationAlgorithm {

    private Graph layout;
    private Ratings ratings;
    private Correlations correlations;

    private SpaceFilter spaceFilter;
    private TimeFilter timeFilter;
    private UserUserFilter userUserFilter;
    private ContentFilter contentFilter;

    public TheAlgorithm(SpaceFilter spaceFilter,
                        TimeFilter timeFilter,
                        UserUserFilter userUserFilter,
                        ContentFilter contentFilter){
        this.spaceFilter = spaceFilter;
        this.timeFilter = timeFilter;
        this.userUserFilter = userUserFilter;
        this.contentFilter = contentFilter;
    }

    @Override
    public double getPrediction(User user, Item item) {
        return 0;
    }

    @Override
    public List<ItemPredictionLink> getTopNForUser(User user, int n) {
        return null;
    }

    public List<ItemPredictionLink> getTopNForUser(User user, int n, int location){
        List<ItemPredictionLink> helperList = new ArrayList<>();

        List<ItemPredictionLink> spaceResult = spaceFilter.getTopNForUser(user, n, location);
        List<ItemPredictionLink> timeResult = timeFilter.getTopNForUser(user, n);
        List<ItemPredictionLink> userResult = userUserFilter.getTopNForUser(user, n);

        /*System.out.println("SpacePrediction");
        spaceResult.forEach(i -> System.out.println(i));
        System.out.println("TimePrediction");
        timeResult.forEach(i -> System.out.println(i));
        System.out.println("UserUserPrediction");
        userResult.forEach(i -> System.out.println(i));*/



        spaceResult
                .stream()
                .forEach(sr -> timeResult.stream().filter(tr -> tr.itemId == sr.itemId).forEach(str -> str.prediction *= sr.prediction));

        timeResult
                .stream()
                .forEach(tr -> userResult.stream().filter(ur -> ur.itemId == tr.itemId).forEach(uts -> uts.prediction *= tr.prediction));


        helperList = userResult;

        helperList.sort(Collections.reverseOrder());
        return helperList.subList(0, n);
    }
}
