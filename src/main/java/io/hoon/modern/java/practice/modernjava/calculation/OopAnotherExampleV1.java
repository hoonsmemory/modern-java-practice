package io.hoon.modern.java.practice.modernjava.calculation;

public class OopAnotherExampleV1 {
    public static void main(String[] args) {
        CalculatorServiceV1 calculatorServiceV1 = new CalculatorServiceV1();
        int addResult = calculatorServiceV1.calculate('+', 1, 1);
        System.out.println(addResult);

        int subResult = calculatorServiceV1.calculate('-', 1, 1);
        System.out.println(subResult);
    }
}

class CalculatorServiceV1 {
    public int calculate(char calc, int num1, int num2) {

        if(calc == '+') {
            return num1 + num2;
        } else if(calc == '-') {
            return num1 - num2;
        } else if(calc == '*') {
            return num1 * num2;
        } else if(calc == '/') {
            return num1 / num2;
        } else {
            throw new IllegalArgumentException("Unknown calculation :" + calc);
        }

    }
}
