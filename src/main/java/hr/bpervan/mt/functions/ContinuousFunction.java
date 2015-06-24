package hr.bpervan.mt.functions;

/**
 * Created by Branimir on 17.6.2015..
 */
public interface ContinuousFunction extends Function{
    double eval(double[] coeffs);
}
