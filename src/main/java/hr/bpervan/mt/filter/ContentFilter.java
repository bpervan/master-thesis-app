package hr.bpervan.mt.filter;

import hr.bpervan.mt.data.Characteristics;
import hr.bpervan.mt.data.ItemPredictionLink;
import hr.bpervan.mt.data.Ratings;
import hr.bpervan.mt.model.Item;
import hr.bpervan.mt.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Branimir on 19.6.2015..
 */
public class ContentFilter implements RecommendationAlgorithm {

    private Ratings ratings;
    private List<Item> items;

    private Map<String, Integer> df;
    private Map<String, Double> idf;

    public ContentFilter(Ratings ratings, List<Item> items) {
        this.ratings = ratings;
        this.items = items;
        this.setup();
    }

    public Ratings getRatings() {
        return ratings;
    }

    public void setRatings(Ratings ratings) {
        this.ratings = ratings;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    private void setup(){

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
