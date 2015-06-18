package hr.bpervan.mt.data;

/**
 * Created by Branimir on 18.6.2015..
 */
public interface Link extends Comparable<Link> {
    int getItemId();
    double getPrediction();
}
