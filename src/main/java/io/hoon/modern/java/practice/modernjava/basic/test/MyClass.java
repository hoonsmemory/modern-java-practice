package io.hoon.modern.java.practice.modernjava.basic.test;

import java.math.BigDecimal;

public class MyClass {

    public static void main(String[] args) {
        println(1, 2, 3, (i1, i2, i3) -> String.valueOf(i1 + i2 + i3));
        println("Functional ", "Interface ", "Test", (s1, s2, s3) -> s1 + s2 + s3);
        println("Area is ", 20, 3, (s1, s2, s3) -> s1 + (s2 * s3));

        BigDecimalToCurrency bigDecimalToCurrency = bd -> "$" + bd.toString();
        String result = bigDecimalToCurrency.toCurrency(new BigDecimal("120.00"));
        System.out.println(result);
    }

    private static <T1, T2, T3> void println(T1 t1, T2 t2, T3 t3, MyFunction<T1, T2, T3, String> function) {
        System.out.println(function.apply(t1, t2, t3));
    }
}
