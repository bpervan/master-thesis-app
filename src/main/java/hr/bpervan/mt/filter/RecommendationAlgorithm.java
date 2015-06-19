package hr.bpervan.mt.filter;

import hr.bpervan.mt.data.ItemPredictionLink;
import hr.bpervan.mt.model.Item;
import hr.bpervan.mt.model.User;

import java.util.List;

/**
 * Created by Branimir on 2.6.2015..
 */
public interface RecommendationAlgorithm {
    double getPrediction(User user, Item item);

    /**
     * @param user
     * @param n
     * @return List of item ids
     * */
    List<ItemPredictionLink> getTopNForUser(User user, int n);
}
