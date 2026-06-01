package org.example.trigFunc;

import org.example.MathFunction;

public class Tan implements MathFunction {
    private final Cos cos;
    private final Sin sin;
    public Tan(Cos cos, Sin sin) {
        this.cos = cos;
        this.sin = sin;
    }
    @Override
    public double calculate(double x, double eps){
        if (Math.abs(cos.calculate(x, eps))< eps) throw new ArithmeticException("tan(x) не определен при cos(x) = 0");
        return sin.calculate(x,eps)/cos.calculate(x,eps);
    }
}
