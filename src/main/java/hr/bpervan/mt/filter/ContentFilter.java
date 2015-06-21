package hr.bpervan.mt.filter;

import hr.bpervan.mt.data.ItemPredictionLink;
import hr.bpervan.mt.data.Ratings;
import hr.bpervan.mt.model.Item;
import hr.bpervan.mt.model.User;

import java.util.*;

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
        df = new HashMap<>();
        idf = new HashMap<>();
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
        int nonNull;
        for(String s : Item.cNames){
            nonNull = 0;
            for(Item i : items){
                Double d = i.characteristicsMap.get(s);
                if(Double.compare(d, 0) != 0){
                    nonNull++;
                }
            }
            df.put(s, nonNull);
            idf.put(s, 1 / (double)nonNull);
        }
    }

    @Override
    public double getPrediction(User user, Item item) {
        return 0;
    }

    @Override
    public List<ItemPredictionLink> getTopNForUser(User user, int n) {
        List<ItemPredictionLink> helperList = new ArrayList<>();

        Map<String, Double> userProfile = new HashMap<>();

        for(String s : Item.cNames){
            double sum = .0;
            for(Item i : items){
                Double d = i.characteristicsMap.get(s);
                Double givenRating = ratings.getValue(user.getUserId(), i.getItemId());
                if(Double.compare(givenRating, -100) != 0){
                    sum += (d * givenRating);
                }
            }
            userProfile.put(s, sum);
        }

        for(Item i : items){
            double sum = .0;
            for(String s : Item.cNames){
                Double d = i.characteristicsMap.get(s);
                Double idfR = idf.get(s);
                Double profile = userProfile.get(s);

                sum += (idfR * profile * d);
            }
            helperList.add(new ItemPredictionLink(
                    i.getItemId(),
                    sum
            ));
        }

        helperList.sort(Collections.reverseOrder());

        if(n > helperList.size()){
            return helperList;
        } else {
            return helperList.subList(0, n);
        }
    }
}
