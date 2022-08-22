package io.hoon.modern.java.practice.modernjava.calculation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OopAnotherExampleV2Test {

    @DisplayName("더하기 테스트")
    @Test
    void testCalculateAdd() {
        Calculation calculation = new Addition();

        int calculate = calculation.calculate(100, 20);

        assertEquals(120, calculate);
    }

    @DisplayName("빼기 테스트")
    @Test
    void testCalculateSub() {
        Calculation calculation = new Subtraction();

        int calculate = calculation.calculate(100, 20);

        assertEquals(80, calculate);
    }

    @DisplayName("곱하기 테스트")
    @Test
    void testCalculateMultiple() {
        Calculation calculation = new Multiplication();

        int calculate = calculation.calculate(100, 20);

        assertEquals(2000, calculate);
    }

    @DisplayName("나누기 테스트")
    @Test
    void testCalculateDivide() {
        Calculation calculation = new Division();

        int calculate = calculation.calculate(100, 0);

        assertEquals(5, calculate);
    }

    @DisplayName("람다 테스트")
    @Test
    void testCalculateLambda() {
        Calculation calculation = (num1, num2) -> num1 + num2;

        int calculate = calculation.calculate(100, 0);

        assertEquals(100, calculate);
    }

}