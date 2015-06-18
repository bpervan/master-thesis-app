package hr.bpervan.mt.recommender;

import hr.bpervan.mt.data.Correlations;
import hr.bpervan.mt.data.ItemPredictionLink;
import hr.bpervan.mt.data.Ratings;
import hr.bpervan.mt.model.Item;
import hr.bpervan.mt.model.User;
import hr.bpervan.mt.space.Graph;

import java.util.List;

/**
 * Created by Branimir on 18.6.2015..
 */
public class TheAlgorithm implements RecommendationAlgorithm {

    private Graph layout;
    private Ratings ratings;
    private Correlations correlations;

    public TheAlgorithm(){

    }

    @Override
    public double getPrediction(User user, Item item) {
        return 0;
    }

    @Override
    public List<ItemPredictionLink> getTopNForUser(User user, int n) {
        return null;
    }
}
