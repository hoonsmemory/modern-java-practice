package io.hoon.modern.java.practice.modernjava.stream.stream;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Parallel Stream 주의 사항
 * ORM의 지연 로딩(Lazy-Loading)과 함께 쓸 수 없다. (원하는 값을 가져올 수 없는 상황이 생길 수 있다.)
 */
public class StreamExamples5Parallel {

    public static void main(String[] args) {

        // 0 : 코어 개수 = 1, 1 : 코어 개수 = 2, 3 : 코어 개수 = 4, 7 : 코어 개수 = 8
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "7");
        String property = System.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism");
        System.out.println("코어 옵션 : " + property);
        //raceCondition();

        //Parallel Stream 장점

        System.out.println("==========================\nParallel Stream");
        long start1 = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .parallelStream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return i;
                })
                .forEach(i -> System.out.println(i));

        long end1 = System.currentTimeMillis();
        System.out.println("걸린 시간 : " + (end1 - start1));

        System.out.println("\n==========================\nStream");
        long start2 = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .stream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return i;
                })
                .forEach(i -> System.out.println(i));

        long end2 = System.currentTimeMillis();
        System.out.println("걸린 시간 : : " + (end2 - start2));
    }

    private static void raceCondition() {
        int[] sum1 = {0};
        IntStream.range(0, 100)
                 .forEach(i -> sum1[0] += i);
        System.out.println("                          stream sum : " + sum1[0]);

        //Race Condition 문제 발생(동시성)
        int[] sum2 = {0};
        IntStream.range(0, 100)
                .parallel()
                .forEach(i -> sum2[0] += i);
        System.out.println("          parallel sum (side-effect) : " + sum2[0]);

        int sum3 = IntStream.range(0, 100)
                            .sum();
        System.out.println("         stream sum (no side-effect) : " + sum3);


        int sum4 = IntStream.range(0, 100)
                            .parallel()
                            .sum();
        System.out.println("parallel stream sum (no side-effect) : " + sum4);
    }
}
