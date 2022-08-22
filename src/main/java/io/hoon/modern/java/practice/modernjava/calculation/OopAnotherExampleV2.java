package io.hoon.modern.java.practice.modernjava.calculation;

/**
 * 단일 책임 원칙 적용
 */
public class OopAnotherExampleV2 {
    public static void main(String[] args) {
        Calculation calculatorServiceV2 = new CalculatorServiceV2(new Addition());
        int addition1 = calculatorServiceV2.calculate(1, 1);
        System.out.println(addition1);

        FtCalculator ftCalculator = new FtCalculator();
        int calculate2 = ftCalculator.calculate(new Addition(), 1, 1);
        System.out.println(calculate2);

        //First
        int calculate3 = ftCalculator.calculate((i1, i2)->i1 + i2, 1, 1);
        System.out.println(calculate3);

        int calculate4 = ftCalculator.calculate((i1, i2)->((i1 + i2) * 2) * i1 + i2, 1, 1);
        System.out.println(calculate4);
    }
}

class CalculatorServiceV2 implements Calculation{

    private Calculation calculation;

    public CalculatorServiceV2(final Calculation calculation) {
        this.calculation = calculation;
    }

    @Override
    public int calculate(int num1, int num2) {
        return this.calculation.calculate(num1, num2);
    }
}

interface Calculation {
    int calculate(int num1, int num2);
}

class Addition implements Calculation {

    @Override
    public int calculate(int num1, int num2) {
        return num1 + num2;
    }
}

class Subtraction implements Calculation {

    @Override
    public int calculate(int num1, int num2) {
        return num1 - num2;
    }
}


class Multiplication implements Calculation {

    @Override
    public int calculate(int num1, int num2) {
        return num1 * num2;
    }
}


class Division implements Calculation {

    @Override
    public int calculate(int num1, int num2) {
        assert num2 != 0;
        return num1 / num2;
    }
}

class FtCalculator {
    public int calculate(Calculation calculation, int num1, int num2) {
        return calculation.calculate(num1, num2);
    }

}


