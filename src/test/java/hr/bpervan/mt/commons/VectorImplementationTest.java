package hr.bpervan.mt.commons;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Branimir on 5.5.2015..
 */
public class VectorImplementationTest {

    private Vector<Double> doubleVector;
    private List<Double> coeffs1;
    private List<Double> coeffs2;

    @Before
    public void setUp() throws Exception {
        this.coeffs1 = Arrays.asList(new Double[]{1.0, 2.0, 3.0});
        this.coeffs2 = Arrays.asList(new Double[]{4.0, 5.0, 6.0});

        this.doubleVector = new VectorImplementation()
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAdd() throws Exception {

    }

    @Test
    public void testSubtract() throws Exception {

    }
}