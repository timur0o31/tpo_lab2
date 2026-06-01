package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.example.logFunc.Ln;
import org.example.logFunc.Log3;
import org.example.trigFunc.*;

public class CsvGenerator {
    private static final Map<String, MathFunction> FUNCTIONS = new HashMap<String, MathFunction>();
    static {
        Cos cos = new Cos();
        Sin sin = new Sin(cos);
        Ln ln = new Ln();
        FUNCTIONS.put("sin", sin);
        FUNCTIONS.put("cos", cos);
        FUNCTIONS.put("tan", new Tan(cos,sin));
        FUNCTIONS.put("cot", new Cot(cos,sin));
        FUNCTIONS.put("sec", new Sec(cos));
        FUNCTIONS.put("csc", new Csc(sin));
        FUNCTIONS.put("ln",ln);
        FUNCTIONS.put("log3",new Log3(ln));
        FUNCTIONS.put("func", new Function(
                cos,sin,FUNCTIONS.get("tan"),
                FUNCTIONS.get("cot"), FUNCTIONS.get("sec"),
                FUNCTIONS.get("csc"),
                ln,
                FUNCTIONS.get("log3")
        ));
    }

    public static void main(String[] args){
        String functionName = args[0].toLowerCase();
        double start = Double.parseDouble(args[1]);
        double end = Double.parseDouble(args[2]);
        double step = Double.parseDouble(args[3]);
        double eps = Double.parseDouble(args[4]);
        MathFunction function = FUNCTIONS.get(functionName);
        if (function == null){
            System.err.println("Unknown function: " + functionName);
            return;
        }
        generateCsv(function,start, end, step, eps, functionName);
    }
    private static void generateCsv(MathFunction function, double start, double end, double step, double eps, String functionName){
        try(PrintWriter out = new PrintWriter(new FileWriter(functionName+".csv"))) {
            out.printf(Locale.US,"x; %s(x)%n", functionName);
            for (double x = start; x <= end; x += step){
                try{
                    double y = function.calculate(x, eps);
                    out.printf(Locale.US,"%.5f; %.5f%n", x, y);
                }catch (Exception e){
                    out.printf(Locale.US,"%.5f; NaN%n", x);
                }
        }
        }catch(IOException e){
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
    }
}
