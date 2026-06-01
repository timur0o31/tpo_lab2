package org.example.logFunc;

import org.example.MathFunction;

public class Log3 implements MathFunction {
    private final Ln ln;
    public Log3(Ln ln) {
        this.ln = ln;
    }
    @Override
    public double calculate(double x, double eps){
        return ln.calculate(x,eps)/ln.calculate(3,eps);
    }
}
