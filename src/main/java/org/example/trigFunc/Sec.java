package org.example.trigFunc;

import org.example.MathFunction;

public class Sec implements MathFunction {
    private final Cos cos;
    public Sec(Cos cos) {
        this.cos = cos;
    }
    @Override
    public double calculate(double x, double eps){
        if (Math.abs(cos.calculate(x, eps))< eps) throw new ArithmeticException("sec(x) не определен при cos(x) = 0");
        return 1/cos.calculate(x,eps);
    }
}
