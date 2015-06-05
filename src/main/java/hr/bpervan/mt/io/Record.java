package hr.bpervan.mt.io;

/**
 * Created by Branimir on 5.6.2015..
 */
public class Record {
    private String timestamp;
    private double rss;
    private int beaconId;

    public Record(String timestamp, double rss, int beaconId){
        this.timestamp = timestamp;
        this.rss = rss;
        this.beaconId = beaconId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public double getRss() {
        return rss;
    }

    public void setRss(double rss) {
        this.rss = rss;
    }

    public int getBeaconId() {
        return beaconId;
    }

    public void setBeaconId(int beaconId) {
        this.beaconId = beaconId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Timestamp: ");
        stringBuilder.append(timestamp);
        stringBuilder.append("\n");

        stringBuilder.append("Beacon ID: ");
        stringBuilder.append(beaconId);
        stringBuilder.append("\n");

        stringBuilder.append("RSS: ");
        stringBuilder.append(rss);
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }
}
