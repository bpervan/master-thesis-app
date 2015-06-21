package hr.bpervan.mt.data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Branimir on 22.6.2015..
 */
public class ItemBeaconLink {
    private String beaconId;
    private int itemId;

    public ItemBeaconLink(String beaconId, int itemId) {
        this.beaconId = beaconId;
        this.itemId = itemId;
    }

    public String getBeaconId() {
        return beaconId;
    }

    public void setBeaconId(String beaconId) {
        this.beaconId = beaconId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public static List<ItemBeaconLink> fromCsv(String relativePath){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(relativePath);

        List<ItemBeaconLink> helperList = new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))){
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                String[] parts = line.split(";");
                helperList.add(new ItemBeaconLink(
                        parts[0],
                        Integer.parseInt(parts[1])
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
        if (!(o instanceof ItemBeaconLink)) return false;

        ItemBeaconLink that = (ItemBeaconLink) o;

        if (getItemId() != that.getItemId()) return false;
        return !(getBeaconId() != null ? !getBeaconId().equals(that.getBeaconId()) : that.getBeaconId() != null);

    }

    @Override
    public int hashCode() {
        int result = getBeaconId() != null ? getBeaconId().hashCode() : 0;
        result = 31 * result + getItemId();
        return result;
    }

    @Override
    public String toString() {
        return "ItemBeaconLink{" +
                "beaconId='" + beaconId + '\'' +
                ", itemId=" + itemId +
                '}';
    }
}
