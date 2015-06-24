package hr.bpervan.mt.filter.settings;

import hr.bpervan.mt.data.Correlations;
import hr.bpervan.mt.data.Ratings;
import hr.bpervan.mt.filter.ContentFilter;
import hr.bpervan.mt.filter.UserUserFilter;
import hr.bpervan.mt.filter.space.SpaceFilter;
import hr.bpervan.mt.filter.time.TimeFilter;
import hr.bpervan.mt.model.Item;
import hr.bpervan.mt.space.Graph;

import java.util.List;

/**
 * Created by Branimir on 24.6.2015..
 */
public class FilterSettingsBuilder {
    private SpaceFilter spaceFilter;
    private TimeFilter timeFilter;

    private UserUserFilter userFilter;
    private ContentFilter contentFilter;

    private Ratings ratings;
    private Correlations correlations;

    private List<Item> itemList;

    private Graph layout;

    private FilterMixType filterMixType;

    private FilterSettingsBuilder(){}

    public static FilterSettingsBuilder getInstance(){
        return new FilterSettingsBuilder();
    }

    public FilterSettingsBuilder setSpaceFilter(SpaceFilter spaceFilter) {
        this.spaceFilter = spaceFilter;
        return this;
    }

    public FilterSettingsBuilder setTimeFilter(TimeFilter timeFilter) {
        this.timeFilter = timeFilter;
        return this;
    }

    public FilterSettingsBuilder setUserFilter(UserUserFilter userFilter) {
        this.userFilter = userFilter;
        return this;
    }

    public FilterSettingsBuilder setContentFilter(ContentFilter contentFilter) {
        this.contentFilter = contentFilter;
        return this;
    }

    public FilterSettingsBuilder setRatings(Ratings ratings) {
        this.ratings = ratings;
        return this;
    }

    public FilterSettingsBuilder setCorrelations(Correlations correlations) {
        this.correlations = correlations;
        return this;
    }

    public FilterSettingsBuilder setItemList(List<Item> itemList) {
        this.itemList = itemList;
        return this;
    }

    public FilterSettingsBuilder setLayout(Graph layout) {
        this.layout = layout;
        return this;
    }

    public FilterSettingsBuilder setFilterMixType(FilterMixType filterMixType) {
        this.filterMixType = filterMixType;
        return this;
    }

    public FilterSettings build(){
        return new FilterSettings(
                ratings,
                correlations,
                itemList,
                layout,
                filterMixType,
                spaceFilter,
                timeFilter,
                userFilter,
                contentFilter
        );
    }
}
