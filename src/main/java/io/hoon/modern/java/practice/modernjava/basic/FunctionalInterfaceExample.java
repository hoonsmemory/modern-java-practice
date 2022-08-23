package io.hoon.modern.java.practice.modernjava.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Functional Interface
 * 인터페이스인데 그 안에 abstract method가 하나만 있는 경우
 */
public class FunctionalInterfaceExample {

    public static void main(String[] args) {
        //Function (T -> R : 일반적인 Function)
        Function<String, Integer> toInt = new Function<String, Integer>() {
            @Override
            public Integer apply(String value) {
                return Integer.parseInt(value);
            }
        };
        Integer num = toInt.apply("1");
        System.out.println(num);


        //Identity Function (T -> T : Identity Function (값 자체를 변형하면 안된다.))
        Function<Integer, Integer> identity = Function.identity();
        Integer apply = identity.apply(100);
        System.out.println(apply);


        //Consumer(return void)
        Consumer<String> print = new Consumer<String>() {
            @Override
            public void accept(String value) {
                System.out.println(value);
            }
        };
        print.accept("Hello");


        //Predicate --> Function(T, Boolean)
        Predicate<Integer> isPositive = value -> value > 0;
        System.out.println(isPositive.test(1));

        List<Integer> numbers = List.of(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);

        List<Integer> positiveNumbers = new ArrayList<>();
        for (Integer number : numbers) {
            if(isPositive.test(number)) {
                positiveNumbers.add(number);
            }
        }
        System.out.println("positive integers " + positiveNumbers);

        //자주 사용할 경우 만들어서 사용하기
        System.out.println(predicateFilter(numbers, isPositive));

        //Supplier
        Supplier<String> supplier = () -> "Hello";
        System.out.println(supplier.get());

        long startTime = System.currentTimeMillis();
        printIfValidIndex(10, ()->getVeryExpensiveValue());
        System.out.println("It took " + (System.currentTimeMillis() - startTime) +" seconds.");
    }

    private static <T> List<T> predicateFilter(List<T> list, Predicate<T> filter) {
        List<T> result = new ArrayList<>();
        for (T number : list) {
            if(filter.test(number)) {
                result.add(number);
            }
        }

        return result;
    }

    private static String getVeryExpensiveValue() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "Return";
    }

    private static void printIfValidIndex(int number, Supplier<String> value) {
        if(number >= 0) {
            System.out.println("The value is " + value);
        } else {
            System.out.println("Invalid");
        }
    }
}
