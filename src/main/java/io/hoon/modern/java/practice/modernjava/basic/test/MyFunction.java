package io.hoon.modern.java.practice.modernjava.basic.test;

@FunctionalInterface
public interface MyFunction <T1, T2, T3, R>{
    R apply(T1 t1, T2 t2, T3 t3);
}
