package hr.bpervan.mt.functions;

/**
 * Created by Branimir on 14.6.2015..
 */
public class Gaussian implements Function{

    private double a;
    private double b;
    private double c;

    /** Empirically found */
    public Gaussian(){
        this.a = 0.23;
        this.b = 2.5;
        this.c = 2;
    }

    public Gaussian(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double eval(double[] coeffs) {
        double mean = Math.abs(
                (coeffs[0] - this.c) / 2
        );

        mean = Math.pow(mean, 2 * this.b);

        return 1 / (1 + mean);
    }
}
