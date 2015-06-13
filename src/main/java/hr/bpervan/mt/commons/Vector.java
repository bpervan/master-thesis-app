package hr.bpervan.mt.commons;

import java.util.Arrays;

/**
 * Created by Branimir on 13.6.2015..
 */
public class Vector {
    private double[] coeffs;

    public Vector(){

    }

    public Vector(double[] coeffs) {
        this.coeffs = coeffs;
    }

    public double[] getCoeffs() {
        return coeffs;
    }

    public void setCoeffs(double[] coeffs) {
        this.coeffs = coeffs;
    }

    public Vector add(Vector that) throws VectorException{
        this.checkDimensions(that.coeffs.length);

        for(int i = 0; i < coeffs.length; ++i){
            this.coeffs[i] += that.coeffs[i];
        }
        return this;
    }

    public Vector sub(Vector that) throws VectorException{
        this.checkDimensions(that.coeffs.length);

        for(int i = 0; i < coeffs.length; ++i){
            this.coeffs[i] -= that.coeffs[i];
        }
        return this;
    }

    public Vector xProduct(Vector that){
        throw new UnsupportedOperationException("Not implemented");
    }

    /** Sumproduct in excel */
    public double dotProoduct(Vector that) throws VectorException{
        this.checkDimensions(that.coeffs.length);

        double acc = .0;
        for(int i = 0; i < coeffs.length; ++i){
            acc += (coeffs[i] + that.coeffs[i]);
        }

        return acc;
    }

    public double dotProduct(Vector that, double angle){
        return this.norm() * that.norm() * Math.cos(angle);
    }

    public double norm(){
        double acc = .0;
        for(double d : coeffs){
            acc += (d * d);
        }
        return Math.sqrt(acc);
    }

    private void checkDimensions(int thatLength) throws VectorException{
        if(this.coeffs.length != thatLength){
            throw new VectorException("Wrong dimensions");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector)) return false;

        Vector vector = (Vector) o;

        return Arrays.equals(getCoeffs(), vector.getCoeffs());

    }

    @Override
    public int hashCode() {
        return getCoeffs() != null ? Arrays.hashCode(getCoeffs()) : 0;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "coeffs=" + Arrays.toString(coeffs) +
                '}';
    }
}
