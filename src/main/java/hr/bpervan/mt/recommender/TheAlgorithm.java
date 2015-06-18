package hr.bpervan.mt.recommender;

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
    private UserUser userUserFilter;

    public TheAlgorithm(SpaceFilter spaceFilter, TimeFilter timeFilter, UserUser userUserFilter){
        this.spaceFilter = spaceFilter;
        this.timeFilter = timeFilter;
        this.userUserFilter = userUserFilter;
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



        helperList.sort(Collections.reverseOrder());
        return helperList.subList(0, n);
    }
}
