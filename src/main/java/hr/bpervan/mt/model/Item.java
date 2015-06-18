package hr.bpervan.mt.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Branimir on 2.6.2015..
 */
public class Item {
    private int itemId;
    private String itemName;
    private int location;

    public Item(int itemId, String itemName, int location) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.location = location;
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

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public static List<Item> fromCsv(String relativePath){
        List<Item> helperList = new ArrayList<>();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(relativePath);

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))){
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                String[] parts = line.split(";");
                helperList.add(new Item(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        Integer.parseInt(parts[2])
                ));
            }

            bufferedReader.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        return helperList;
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
