package hr.bpervan.mt.model;

import hr.bpervan.mt.utils.StringUtils;

import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Branimir on 17.6.2015..
 */
public class ItemBuilder {
    private int itemId;
    private String itemName;
    private int location;
    private Map<String, Double> map;

    private ItemBuilder(){
        this.itemId = -1;
        this.itemName = null;
        this.location = 0;
        this.map = null;
    }

    public static ItemBuilder getInstance(){
        return new ItemBuilder();
    }

    public ItemBuilder setItemId(int itemId){
        this.itemId = itemId;
        return this;
    }

    public ItemBuilder setItemName(String itemName){
        this.itemName = itemName;
        return this;
    }

    public ItemBuilder setLocation(int location){
        this.location = location;
        return this;
    }

    public ItemBuilder setCharacteristicsMap(Map<String, Double> map){
        this.map = map;
        return this;
    }

    public Item build(){
        return new Item(itemId, itemName, location, map);
    }
}
