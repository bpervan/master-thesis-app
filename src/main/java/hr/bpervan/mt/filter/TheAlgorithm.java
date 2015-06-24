package hr.bpervan.mt.filter;

import hr.bpervan.mt.data.Correlations;
import hr.bpervan.mt.data.ItemPredictionLink;
import hr.bpervan.mt.data.Ratings;
import hr.bpervan.mt.filter.settings.FilterMixType;
import hr.bpervan.mt.filter.space.SpaceFilterImplementation;
import hr.bpervan.mt.filter.time.TimeFilterImplementation;
import hr.bpervan.mt.model.Item;
import hr.bpervan.mt.model.User;
import hr.bpervan.mt.space.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Branimir on 18.6.2015..
 */
public class TheAlgorithm implements DataFilter {

    private Graph layout;
    private Ratings ratings;
    private Correlations correlations;

    private SpaceFilterImplementation spaceFilter;
    private TimeFilterImplementation timeFilter;
    private UserUserFilter userUserFilter;
    private ContentFilter contentFilter;
    private FilterMixType filterMixType;
    private double mixRatio;

    public TheAlgorithm(SpaceFilterImplementation spaceFilter,
                        TimeFilterImplementation timeFilter,
                        UserUserFilter userUserFilter,
                        ContentFilter contentFilter,
                        FilterMixType filterMixType,
                        double mixRatio){
        this.spaceFilter = spaceFilter;
        this.timeFilter = timeFilter;
        this.userUserFilter = userUserFilter;
        this.contentFilter = contentFilter;
        this.filterMixType = filterMixType;
        this.mixRatio = mixRatio;
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

        List<ItemPredictionLink> spaceResult = spaceFilter.getTopNForUser(user, n, location);
        List<ItemPredictionLink> timeResult = timeFilter.getTopNForUser(user, n);
        List<ItemPredictionLink> userResult = userUserFilter.getTopNForUser(user, n);
        List<ItemPredictionLink> contentResult = contentFilter.getTopNForUser(user, n);

        System.out.println("SpacePrediction");
        spaceResult.forEach(i -> System.out.println(i));
        System.out.println();
        System.out.println("TimePrediction");
        timeResult.forEach(i -> System.out.println(i));
        System.out.println();
        System.out.println("UserUserPrediction");
        userResult.forEach(i -> System.out.println(i));
        System.out.println();
        System.out.println("ContentPrediction");
        contentResult.forEach(i -> System.out.println(i));


        spaceResult
                .stream()
                .forEach(sr -> timeResult.stream().filter(tr -> tr.itemId == sr.itemId).forEach(str -> str.prediction *= sr.prediction));

        switch (filterMixType){
            case CONTENT_FILTER_ONLY:
                timeResult
                        .stream()
                        .forEach(tr -> contentResult
                                .stream()
                                .filter(cr -> cr.itemId == tr.itemId)
                                .forEach(cts -> cts.prediction *= tr.prediction));
                break;


            case COLLABORATIVE_FILTER_ONLY:
                timeResult
                        .stream()
                        .forEach(tr -> userResult
                                .stream()
                                .filter(ur -> ur.itemId == tr.itemId)
                                .forEach(uts -> uts.prediction *= tr.prediction));
                break;


            case BOTH_FILTERS:
                timeResult
                        .stream()
                        .forEach(tr -> userResult
                                .stream()
                                .filter(ur -> ur.itemId == tr.itemId)
                                .forEach(uts -> uts.prediction *= tr.prediction));

                userResult
                        .stream()
                        .forEach(tr -> contentResult
                                .stream()
                                .filter(cr -> cr.itemId == tr.itemId)
                                .forEach(cts -> cts.prediction *= (tr.prediction * (1 - mixRatio))));

                break;
        }
        helperList = userResult;

        helperList.sort(Collections.reverseOrder());
        if(n > helperList.size()){
            return helperList;
        } else {
            return helperList.subList(0, n);
        }
    }
}
