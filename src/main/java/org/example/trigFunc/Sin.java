package org.example.trigFunc;

import org.example.MathFunction;

public class Sin implements MathFunction {
    private final MathFunction cos;
    public Sin(MathFunction cos) {
        this.cos = cos;
    }
    @Override
    public double calculate(double x, double eps){
        return cos.calculate(Math.PI/2-x,eps);
    }
}
