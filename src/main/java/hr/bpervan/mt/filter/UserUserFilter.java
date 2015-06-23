package hr.bpervan.mt.filter;


import hr.bpervan.mt.data.Correlations;
import hr.bpervan.mt.data.ItemPredictionLink;
import hr.bpervan.mt.data.Ratings;
import hr.bpervan.mt.data.UserCorrelationLink;
import hr.bpervan.mt.model.Item;
import hr.bpervan.mt.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Branimir on 17.6.2015..
 */
public class UserUserFilter implements DataFilter{


    private static final int NEIGHBOURHOOD_SIZE = 5;

    private Ratings ratings;
    private Correlations correlations;

    public UserUserFilter(Ratings ratings, Correlations correlations) {
        this.ratings = ratings;
        this.correlations = correlations;
    }

    public Ratings getRatings() {
        return ratings;
    }

    public void setRatings(Ratings ratings) {
        this.ratings = ratings;
    }

    public Correlations getCorrelations() {
        return correlations;
    }

    public void setCorrelations(Correlations correlations) {
        this.correlations = correlations;
    }

    @Override
    public double getPrediction(User user, Item item) {
        List<UserCorrelationLink> neighbourhood = this.correlations.getNeighbourhood(user.getUserId(), NEIGHBOURHOOD_SIZE);

        double numeratorSum = .0;
        double correlSum = .0;
        for (int i = 0; i < NEIGHBOURHOOD_SIZE; i++) {
            UserCorrelationLink workingNeighbour = neighbourhood.get(i);
            double diff =
                    ratings.getValue(workingNeighbour.userId, item.getItemId()) -
                            ratings.getAverageGivenByUser(workingNeighbour.userId);

            /** Zbrajamo samo ako dodijenjena ocjena postoji*/
            if(Double.compare(ratings.getValue(workingNeighbour.userId, item.getItemId()), -100.0) != 0){
                numeratorSum += (diff * correlations.getCorrelation(user.getUserId(), workingNeighbour.userId));
                correlSum += correlations.getCorrelation(user.getUserId(), workingNeighbour.userId);
            }
        }

        double res = (numeratorSum / correlSum) + ratings.getAverageGivenByUser(user.getUserId());

        return res;
    }

    @Override
    public List<ItemPredictionLink> getTopNForUser(User user, int n) {
        List<ItemPredictionLink> helperList = new ArrayList<>();
        List<UserCorrelationLink> neighbourhood = this.correlations.getNeighbourhood(user.getUserId(), NEIGHBOURHOOD_SIZE);

        for(Integer itemId : ratings.getItemSet()){
            double numeratorSum = .0;
            double correlSum = .0;
            for (int i = 0; i < NEIGHBOURHOOD_SIZE; i++) {
                UserCorrelationLink workingNeighbour = neighbourhood.get(i);
                double diff =
                        ratings.getValue(workingNeighbour.userId, itemId) -
                                ratings.getAverageGivenByUser(workingNeighbour.userId);

                /** Zbrajamo samo ako dodijenjena ocjena postoji*/
                if(Double.compare(ratings.getValue(workingNeighbour.userId, itemId), -100.0) != 0){
                    numeratorSum += (diff * correlations.getCorrelation(user.getUserId(), workingNeighbour.userId));
                    correlSum += correlations.getCorrelation(user.getUserId(), workingNeighbour.userId);
                }
            }

            double res = (numeratorSum / correlSum) + ratings.getAverageGivenByUser(user.getUserId());
            helperList.add(new ItemPredictionLink(itemId, res));
        }

        Collections.sort(helperList, Collections.reverseOrder());

        if(n > helperList.size()){
            return helperList;
        } else {
            return helperList.subList(0, n);
        }
    }

}
