package hr.bpervan.mt.data;

/**
 * Created by Branimir on 18.6.2015..
 */
public class LocationPredictionLink implements Link {

    public int itemId;
    public double prediction;

    public LocationPredictionLink(int itemId, double prediction) {
        this.itemId = itemId;
        this.prediction = prediction;
    }

    @Override
    public int getItemId() {
        return itemId;
    }

    @Override
    public double getPrediction() {
        return prediction;
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
        if (!(o instanceof LocationPredictionLink)) return false;

        LocationPredictionLink that = (LocationPredictionLink) o;

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
    public int compareTo(Link o) {
        LocationPredictionLink that = (LocationPredictionLink)o;
        return Double.compare(this.prediction, that.prediction);
    }
}
