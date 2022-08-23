package io.hoon.modern.java.practice.modernjava.basic.test;

import java.math.BigDecimal;

@FunctionalInterface
public interface BigDecimalToCurrency {
    String toCurrency(BigDecimal value);
}
