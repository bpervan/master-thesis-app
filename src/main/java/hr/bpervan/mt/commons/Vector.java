package hr.bpervan.mt.commons;

import java.util.List;

/**
 * Created by Branimir on 5.5.2015..
 */
public interface Vector<T extends Double> {

    Vector add(Vector<T> that);
    Vector subtract(Vector<T> that);
    Vector dotProduct(Vector<T> that);
    Vector xProduct(Vector<T> that);

    List<T> getCoeffs();

    double abs();
    double norm();

}
