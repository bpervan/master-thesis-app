package hr.bpervan.mt.io;

import com.sun.istack.internal.Nullable;

/**
 * Created by Branimir on 13.6.2015..
 */
public class Timestamp {

    private int hours;
    private int minutes;
    private int seconds;
    private int miliseconds;

    public Timestamp(int hours, int minutes, int seconds, int miliseconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.miliseconds = miliseconds;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMiliseconds() {
        return miliseconds;
    }

    public void setMiliseconds(int miliseconds) {
        this.miliseconds = miliseconds;
    }

    @Nullable
    public static Timestamp fromString(String input){
        String[] parts = input.split(":");
        String[] secsAndMilis = parts[2].split("\\.");

        Timestamp retTimestamp;
        retTimestamp = new Timestamp(
            Integer.parseInt(parts[0]),
            Integer.parseInt(parts[1]),
            Integer.parseInt(secsAndMilis[0]),
            Integer.parseInt(secsAndMilis[1])
        );

        return retTimestamp;
    }

    @Override
    public String toString() {
        return "Timestamp{" +
                "hours=" + hours +
                ", minutes=" + minutes +
                ", seconds=" + seconds +
                ", miliseconds=" + miliseconds +
                '}';
    }
}
