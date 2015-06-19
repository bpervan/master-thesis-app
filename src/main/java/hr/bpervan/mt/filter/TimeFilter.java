package hr.bpervan.mt.filter;

import hr.bpervan.mt.data.ItemPredictionLink;
import hr.bpervan.mt.data.Ratings;
import hr.bpervan.mt.model.Item;
import hr.bpervan.mt.model.User;

import java.util.*;

/**
 * Created by Branimir on 17.6.2015..
 */
public class TimeFilter implements RecommendationAlgorithm {

    private Ratings ratings;

    public TimeFilter(Ratings ratings) {
        this.ratings = ratings;
    }

    @Override
    public double getPrediction(User user, Item item) {
        Calendar c = Calendar.getInstance();
        /** c.get() returns 0 for Sunday, 1 for Monday...*/
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek == 1){
            dayOfWeek = 7;
        } else {
            dayOfWeek = dayOfWeek - 1;
        }

        int hours = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);

        double resHours = hours / 24.0;
        double resMinutes = minutes / 1440.0;

        double resTime = dayOfWeek + resHours + resMinutes;

        return user.getTimeMap().get(item.getItemId()).eval(new double[]{resTime});
    }

    @Override
    public List<ItemPredictionLink> getTopNForUser(User user, int n) {
        List<ItemPredictionLink> helperList = new ArrayList<>();

        Calendar c = Calendar.getInstance();
        /** c.get() returns 0 for Sunday, 1 for Monday...*/
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek == 1){
            dayOfWeek = 7;
        } else {
            dayOfWeek = dayOfWeek - 1;
        }

        int hours = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);

        double resHours = hours / 24.0;
        double resMinutes = minutes / 1440.0;

        double resTime = dayOfWeek + resHours + resMinutes;

        for(Integer itemId : ratings.getItemSet()){
            helperList.add(new ItemPredictionLink(
                    itemId,
                    user.getTimeMap().get(itemId).eval(new double[]{resTime})
            ));
        }

        Collections.sort(helperList, Collections.reverseOrder());

        if(n > helperList.size()){
            return helperList;
        } else {
            return helperList.subList(0, n);
        }
    }
}
