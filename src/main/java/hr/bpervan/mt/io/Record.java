package hr.bpervan.mt.io;

import java.io.Serializable;

/**
 * Created by Branimir on 5.6.2015..
 */
public class Record implements Serializable{
    //18:27:07.525 # 74278bda-b644-4520-8f0c-720eaf059935-baba-bebe $ -94 # 7.9;-5.5;1.9 # -40.9;1;-15.6

    private Timestamp timestamp;
    private String beaconId;
    private int rss;
    private Coordinate3D accelerometer;
    private Coordinate3D magnetometer;

    public Record(Timestamp timestamp, String beaconId, int rss, Coordinate3D accelerometer, Coordinate3D magnetometer) {
        this.timestamp = timestamp;
        this.beaconId = beaconId;
        this.rss = rss;
        this.accelerometer = accelerometer;
        this.magnetometer = magnetometer;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getBeaconId() {
        return beaconId;
    }

    public void setBeaconId(String beaconId) {
        this.beaconId = beaconId;
    }

    public int getRss() {
        return rss;
    }

    public void setRss(int rss) {
        this.rss = rss;
    }

    public Coordinate3D getAccelerometer() {
        return accelerometer;
    }

    public void setAccelerometer(Coordinate3D accelerometer) {
        this.accelerometer = accelerometer;
    }

    public Coordinate3D getMagnetometer() {
        return magnetometer;
    }

    public void setMagnetometer(Coordinate3D magnetometer) {
        this.magnetometer = magnetometer;
    }

    @Override
    public String toString() {
        return "Record{" +
                "timestamp=" + timestamp +
                ", beaconId='" + beaconId + '\'' +
                ", rss=" + rss +
                ", accelerometer=" + accelerometer +
                ", magnetometer=" + magnetometer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Record)) return false;

        Record record = (Record) o;

        if (getRss() != record.getRss()) return false;
        if (!getTimestamp().equals(record.getTimestamp())) return false;
        if (!getBeaconId().equals(record.getBeaconId())) return false;
        if (!getAccelerometer().equals(record.getAccelerometer())) return false;
        return getMagnetometer().equals(record.getMagnetometer());

    }

    @Override
    public int hashCode() {
        int result = getTimestamp().hashCode();
        result = 31 * result + getBeaconId().hashCode();
        result = 31 * result + getRss();
        result = 31 * result + getAccelerometer().hashCode();
        result = 31 * result + getMagnetometer().hashCode();
        return result;
    }
}
