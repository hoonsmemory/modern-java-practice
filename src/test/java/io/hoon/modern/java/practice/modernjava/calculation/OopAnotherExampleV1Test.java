package io.hoon.modern.java.practice.modernjava.calculation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OopAnotherExampleV1Test {

    @DisplayName("더하기 테스트")
    @Test
    void testCalculateAdd() {
        CalculatorServiceV1 calculatorServiceV1 = new CalculatorServiceV1();

        int calculate = calculatorServiceV1.calculate('+', 100, 20);

        assertEquals(120, calculate);
    }

    @DisplayName("빼기 테스트")
    @Test
    void testCalculateSub() {
        CalculatorServiceV1 calculatorServiceV1 = new CalculatorServiceV1();

        int calculate = calculatorServiceV1.calculate('-', 100, 20);

        assertEquals(80, calculate);
    }

    @DisplayName("곱하기 테스트")
    @Test
    void testCalculateMultiple() {
        CalculatorServiceV1 calculatorServiceV1 = new CalculatorServiceV1();

        int calculate = calculatorServiceV1.calculate('*', 100, 20);

        assertEquals(2000, calculate);
    }

    @DisplayName("나누기 테스트")
    @Test
    void testCalculateDivide() {
        CalculatorServiceV1 calculatorServiceV1 = new CalculatorServiceV1();

        int calculate = calculatorServiceV1.calculate('/', 100, 20);

        assertEquals(5, calculate);
    }

}