package hr.bpervan.mt.filter.settings;

import com.sun.istack.internal.Nullable;
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
public class FilterSettings {
    private Ratings ratings;
    private Correlations correlations;
    private List<Item> itemList;
    private Graph layout;
    private FilterMixType filterMixType;

    @Nullable
    private SpaceFilter spaceFilter;
    @Nullable
    private TimeFilter timeFilter;
    @Nullable
    private UserUserFilter userFilter;
    @Nullable
    private ContentFilter contentFilter;

    public FilterSettings(Ratings ratings,
                          Correlations correlations,
                          List<Item> itemList,
                          Graph layout,
                          FilterMixType filterMixType,
                          SpaceFilter spaceFilter,
                          TimeFilter timeFilter,
                          UserUserFilter userFilter,
                          ContentFilter contentFilter) {
        this.ratings = ratings;
        this.correlations = correlations;
        this.itemList = itemList;
        this.layout = layout;
        this.filterMixType = filterMixType;
        this.spaceFilter = spaceFilter;
        this.timeFilter = timeFilter;
        this.userFilter = userFilter;
        this.contentFilter = contentFilter;
    }

    public Ratings getRatings() {
        return ratings;
    }

    public Correlations getCorrelations() {
        return correlations;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public Graph getLayout() {
        return layout;
    }

    public FilterMixType getFilterMixType() {
        return filterMixType;
    }

    public SpaceFilter getSpaceFilter() {
        return spaceFilter;
    }

    public TimeFilter getTimeFilter() {
        return timeFilter;
    }

    public UserUserFilter getUserFilter() {
        return userFilter;
    }

    public ContentFilter getContentFilter() {
        return contentFilter;
    }
}
