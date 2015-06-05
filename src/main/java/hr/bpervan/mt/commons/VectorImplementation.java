package hr.bpervan.mt.commons;

import java.util.List;

/**
 * Created by Branimir on 5.5.2015..
 */
public class VectorImplementation implements Vector<Double>{

    private List<Double> coeffs;

    public VectorImplementation(List<Double> coeffs){
        this.coeffs = coeffs;
    }

    @Override
    public Vector add(Vector<Double> that) {
        for(int i = 0; i < coeffs.size(); ++i){
            this.coeffs.set(i, this.coeffs.get(i) + that.getCoeffs().get(i));
        }
        return this;
    }

    @Override
    public Vector subtract(Vector<Double> that) {
        for(int i = 0; i < coeffs.size(); ++i){
            this.coeffs.set(i, this.coeffs.get(i) - that.getCoeffs().get(i));
        }
        return this;
    }

    @Override
    public Vector dotProduct(Vector<Double> that) {
        return null;
    }

    @Override
    public Vector xProduct(Vector<Double> that) {
        return null;
    }

    @Override
    public List getCoeffs() {
        return this.coeffs;
    }

    @Override
    public double abs() {
        return 0;
    }

    @Override
    public double norm() {
        return 0;
    }
}
