package org.example.trigFunc;

import org.example.MathFunction;

public class Cos implements MathFunction {
    @Override
    public double calculate(double x, double eps){
        if (Double.isNaN(x) || Double.isInfinite(x)){
            throw new IllegalArgumentException("x должен быть исчисляемым");
        }
        if (eps<=0 || eps>1)
            throw new IllegalArgumentException("eps должен принадлежать (0;1]");
        if (x<0) x = x*(-1);
        if (x>0) x = x%(2*Math.PI);
        double res = 1;
        double term = 1;
        double x2 = x*x;
        int k=0;
        while (true){
            term *= -x2 / ((2*k+1)*(2*k+2));
            res += term;
            k++;
            if (Math.abs(term)<eps){
                break;
            }
            if (k>1000)
                throw new ArithmeticException("Ряд не сошелся за 1000 итераций");
        }
        return res;
    }
}
