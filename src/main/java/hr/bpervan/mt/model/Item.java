package hr.bpervan.mt.model;

/**
 * Created by Branimir on 2.6.2015..
 */
public class Item {
    private int itemId;
    private String itemName;

    public Item(int itemId, String itemName) {
        this.itemId = itemId;
        this.itemName = itemName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (getItemId() != item.getItemId()) return false;
        return !(getItemName() != null ? !getItemName().equals(item.getItemName()) : item.getItemName() != null);

    }

    @Override
    public int hashCode() {
        int result = getItemId();
        result = 31 * result + (getItemName() != null ? getItemName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}
