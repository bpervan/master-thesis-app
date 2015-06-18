package hr.bpervan.mt.model;

/**
 * Created by Branimir on 17.6.2015..
 */
public class ItemBuilder {
    private int itemId;
    private String itemName;
    private int location;

    private ItemBuilder(){
        this.itemId = -1;
        this.itemName = null;
        this.location = 0;
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

    public Item build(){
        return new Item(itemId, itemName, location);
    }
}
