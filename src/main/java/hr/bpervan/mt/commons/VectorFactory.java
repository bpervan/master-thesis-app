package hr.bpervan.mt.commons;

import java.util.List;

/**
 * Created by Branimir on 5.5.2015..
 */
public class VectorFactory {

    private static VectorFactory instance;
    private VectorFactory(){}

    public static VectorFactory getInstance(){
        if(instance == null){
            synchronized (VectorFactory.class){
                if(instance == null){
                    instance = new VectorFactory();
                }
            }
        }
        return instance;
    }

    public Vector createVector(List<Double> coeffs){
        return new VectorImplementation(coeffs);
    }

}
