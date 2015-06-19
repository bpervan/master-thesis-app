package hr.bpervan.mt.filter;

import hr.bpervan.mt.data.Characteristics;
import hr.bpervan.mt.data.ItemPredictionLink;
import hr.bpervan.mt.model.Item;
import hr.bpervan.mt.model.User;

import java.util.List;

/**
 * Created by Branimir on 19.6.2015..
 */
public class ContentFilter implements RecommendationAlgorithm {

    private Characteristics characteristics;

    public ContentFilter(Characteristics characteristics){
        this.characteristics = characteristics;
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
