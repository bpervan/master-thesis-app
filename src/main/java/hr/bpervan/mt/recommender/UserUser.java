package hr.bpervan.mt.recommender;

import hr.bpervan.mt.data.RatingTable;

/**
 * Created by Branimir on 15.6.2015..
 */
public class UserUser implements RecommendationAlgorithm{

    private RatingTable userItemRatingTable;

    public UserUser(RatingTable userItemRatingTable){
        this.userItemRatingTable = userItemRatingTable;
    }


    @Override
    public void getRecommendation() {

    }
}
