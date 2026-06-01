package org.example.trigFunc;

import org.example.MathFunction;

public class Cot implements MathFunction {
    private final Cos cos;
    private final Sin sin;
    public Cot(Cos cos, Sin sin) {
        this.cos = cos;
        this.sin = sin;
    }
    @Override
    public double calculate(double x, double eps){
        if (Math.abs(sin.calculate(x, eps)) < eps) throw new ArithmeticException("cot(x) не определен при sin(x)=0");
        return cos.calculate(x,eps)/sin.calculate(x,eps);
    }
}
