package hr.bpervan.mt.recommender;

import hr.bpervan.mt.data.ItemPredictionLink;
import hr.bpervan.mt.model.Item;
import hr.bpervan.mt.model.User;

import java.util.List;

/**
 * Created by Branimir on 17.6.2015..
 */
public class TheAlgorithm implements RecommendationAlgorithm{

    public TheAlgorithm(){}

    public void spacialFilter(){

    }

    public void timeFilter(){

    }

    public void collaborativeFilter(){

    }

    public void contentFilter(){

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
