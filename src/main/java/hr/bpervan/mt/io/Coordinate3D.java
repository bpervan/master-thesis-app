package hr.bpervan.mt.io;

/**
 * Created by Branimir on 13.6.2015..
 */
public class Coordinate3D {

    private double x;
    private double y;
    private double z;

    private double abs;

    public Coordinate3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.abs = Math.sqrt(x * x + y * y + z * z);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getAbs() {
        return abs;
    }

    public void setAbs(double abs) {
        this.abs = abs;
    }

    public static Coordinate3D fromString(String input){
        String[] parts = input.split(";");
        return new Coordinate3D(
                Double.parseDouble(parts[0]),
                Double.parseDouble(parts[1]),
                Double.parseDouble(parts[2])
        );
    }

    @Override
    public String toString() {
        return "Coordinate3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", abs=" + abs +
                '}';
    }
}
