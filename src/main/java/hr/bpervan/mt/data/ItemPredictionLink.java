package hr.bpervan.mt.data;

/**
 * Created by Branimir on 17.6.2015..
 */
@Link
public class ItemPredictionLink implements Comparable<ItemPredictionLink>{
    public int itemId;
    public double prediction;

    public ItemPredictionLink(int itemId, double prediction) {
        this.itemId = itemId;
        this.prediction = prediction;
    }

    @Override
    public String toString() {
        return "ItemPredictionLink{" +
                "itemId=" + itemId +
                ", prediction=" + prediction +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemPredictionLink)) return false;

        ItemPredictionLink that = (ItemPredictionLink) o;

        if (itemId != that.itemId) return false;
        return Double.compare(that.prediction, prediction) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = itemId;
        temp = Double.doubleToLongBits(prediction);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public int compareTo(ItemPredictionLink o) {
        return Double.compare(this.prediction, o.prediction);
    }
}
