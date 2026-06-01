package org.example.logFunc;

import org.example.MathFunction;

public class Ln implements MathFunction {
    @Override
    public double calculate(double x, double eps){
        if (eps<=0)
            throw new IllegalArgumentException("Погрешность должна быть положительной");
        if (x<=0)
            throw new IllegalArgumentException("Аргумент должен быть больше 0");
        double y = (x-1)/(x+1);
        double sum =0;
        double pow = y;
        int n = 0;
        while (true){
            double term = pow / (2*n+1);
            if (Math.abs(term)<eps/2) break;
            n++;
            sum += term;
            pow *= y*y;
        }
        return sum*2;
    }
}
