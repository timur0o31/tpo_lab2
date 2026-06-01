package org.example;

public class Function implements MathFunction {
    private final MathFunction ln;
    private final MathFunction log3;
    private final MathFunction cos;
    private final MathFunction sin;
    private final MathFunction tan;
    private final MathFunction cot;
    private final MathFunction sec;
    private final MathFunction csc;
    public Function(MathFunction cos, MathFunction sin, MathFunction tan,
                    MathFunction cot, MathFunction sec,
                    MathFunction csc, MathFunction ln, MathFunction log3) {
        this.cos = cos;
        this.sin = sin;
        this.tan = tan;
        this.cot = cot;
        this.sec = sec;
        this.csc = csc;
        this.ln = ln;
        this.log3 = log3;
    }
    @Override
    public double calculate(double x, double eps) {
        if (x<=0){
            return ((((
                    Math.pow(Math.pow(sec.calculate(x,eps),3) * cot.calculate(x,eps),2)
                    - (sec.calculate(x,eps)-csc.calculate(x,eps)))
                    / sin.calculate(x,eps))
                    - (sin.calculate(x,eps)/tan.calculate(x,eps)))
                    -((sin.calculate(x,eps)/cos.calculate(x,eps)/csc.calculate(x,eps))
            ));
        }else{
            return Math.pow(
                    ((((log3.calculate(x,eps)+ log3.calculate(x,eps))*ln.calculate(x,eps))
                            *ln.calculate(x,eps))
                    -ln.calculate(x,eps)),2);
        }
    }
}
