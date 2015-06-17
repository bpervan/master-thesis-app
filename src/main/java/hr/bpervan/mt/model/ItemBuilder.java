package hr.bpervan.mt.model;

/**
 * Created by Branimir on 17.6.2015..
 */
public class ItemBuilder {
    private int itemId;
    private String itemName;

    private ItemBuilder(){
        this.itemId = -1;
        this.itemName = null;
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

    public Item build(){
        return new Item(itemId, itemName);
    }
}
