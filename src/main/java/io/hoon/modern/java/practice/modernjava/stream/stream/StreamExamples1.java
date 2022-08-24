package io.hoon.modern.java.practice.modernjava.stream.stream;

import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExamples1 {
    public static void main(String[] args) {
        IntStream.range(0, 10).forEach(i-> System.out.print(i + " "));

        /**
         * 용BigInteger.ONE, i -> i.add(BigInteger.ONE))
         * BigInteger.ONE : 가장 첫 번째 값을 의미한다.
         * i -> i.add(BigInteger.ONE) : i는 첫 번째 값과 그 이후에 계산되는 값을 가진다.
         *                              i.add(BigInteger.ONE)는 1씩 더한다.
         *
         * forEach(i -> System.out.print(i + " "))
         * i : iterate에서 계산된 i의 값을 가져온다.
         * System.out.print(i + " ") : println 수행한다.
         */
        Stream.iterate(BigInteger.ONE, i -> i.add(BigInteger.ONE))
              .forEach(i -> System.out.print(i + " "));


    }
}
