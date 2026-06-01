package org.example.trigFunc;

import org.example.MathFunction;

public class Csc implements MathFunction {
    private final Sin sin;
    public Csc(final Sin sin){
        this.sin = sin;
    }
    public double calculate(double x, double eps){
        if (Math.abs(sin.calculate(x, eps))< eps) throw new ArithmeticException("csc(x) не определен при sin(x)= 0");
        return 1/sin.calculate(x,eps);
    }
}
