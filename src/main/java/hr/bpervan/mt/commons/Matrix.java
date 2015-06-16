package hr.bpervan.mt.commons;

import java.util.Arrays;

/**
 * Created by Branimir on 14.6.2015..
 */
public class Matrix {

    private double[][] coeffs;
    private int cols;
    private int rows;

    public Matrix(double[][] that) throws MatrixException{
        if(that.length == 0){
            throw new MatrixException("Input coeffs 0");
        }

        this.coeffs = new double[that.length][that[0].length];

        this.rows = that.length;
        this.cols = that[0].length;

        for(int i = 0; i < coeffs.length; ++i){
            coeffs[i] = Arrays.copyOf(that[i], that[0].length);
        }
    }

    public Matrix(int rows, int cols){
        this.coeffs = new double[rows][cols];
        this.rows = rows;
        this.cols = cols;
    }

    public Matrix copy() throws MatrixException{
        return new Matrix(coeffs);
    }

    public Matrix add(Matrix that) throws MatrixException{
        this.checkDimensions(that);
        for(int i = 0; i < rows; ++i){
            for(int j = 0; j < cols; ++j){
                coeffs[i][j] += that.coeffs[i][j];
            }
        }
        return this;
    }

    public Matrix sub(Matrix that) throws MatrixException{
        this.checkDimensions(that);
        for(int i = 0; i < rows; ++i){
            for(int j = 0; j < cols; ++j){
                coeffs[i][j] -= that.coeffs[i][j];
            }
        }
        return this;
    }

    public Matrix transpose(){
        double[][] temp = new double[cols][rows];
        for(int i = 0; i < rows; ++i){
            for(int j = 0; j < cols; ++j){
                temp[j][i] = coeffs[i][j];
            }
        }
        int temprows = rows;
        rows = cols;
        cols = temprows;
        coeffs = temp;
        return this;
    }

    private void checkDimensions(Matrix that) throws MatrixException{
        if((this.rows != that.rows) || (this.cols != that.cols)){
            throw new MatrixException("Wrong dimensions");
        }
    }
}
