package hr.bpervan.mt.model;

import hr.bpervan.mt.data.Characteristics;
import hr.bpervan.mt.utils.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Branimir on 2.6.2015..
 */
public class Item {
    private int itemId;
    private String itemName;
    private int location;

    public Map<String, Double> characteristicsMap;

    public Item(int itemId, String itemName, int location, Map<String, Double> inMap) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.location = location;

        characteristicsMap = inMap;
        this.normalizeMap();
    }

    private void normalizeMap(){
        long nonZeroCount = characteristicsMap
                .entrySet()
                .stream()
                .filter(p -> Double.compare(p.getValue(), 0) != 0)
                .count();

        double norm = 1 / Math.sqrt(nonZeroCount);

        characteristicsMap
                .entrySet()
                .stream()
                .filter(p -> Double.compare(p.getValue(), 0) != 0)
                .forEach(e -> e.setValue(norm));
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
            String line = bufferedReader.readLine();
            String[] partsPreliminary = line.split("#");
            String[] characteristicsNames = partsPreliminary[1].split(";");

            while((line = bufferedReader.readLine()) != null){
                String[] parts = line.split("#");
                String itemData = parts[0];
                String characteristicsData = parts[1];

                String[] item = itemData.split(";");
                String[] characteristic = characteristicsData.split(";");

                Map<String, Double> tempMap = new HashMap<>();
                for(int i = 0; i < characteristic.length; ++i){
                    tempMap.put(characteristicsNames[i], Double.parseDouble(characteristic[i]));
                }

                helperList.add(new Item(
                        Integer.parseInt(item[0]),
                        item[1],
                        Integer.parseInt(item[2]),
                        tempMap
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
