package io.hoon.modern.java.practice.modernjava.methodreference;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MethodReferenceExamples {
    public static void main(String[] args) {

        methodReference02();

    }

    //Method Reference 기본
    private static void methodReference01() {
        List<Integer> list1 = List.of(1, 2, 3, 4, 5);
        list1.stream()
            .forEach(System.out::println);


        //1. 주어진 Object Member Method 의 Method Reference 사용
        List<BigDecimal> list2 = List.of(new BigDecimal("10.0"), new BigDecimal("23"), new BigDecimal("5"));
        list2.stream()
                .sorted(BigDecimal::compareTo) //BigDecimal = compareTo를 사용할 값 :: compareTo = 비교대상이될 값
                .collect(Collectors.toList())
                .forEach(System.out::print);


        //2. Static Method 의 Method Reference 사용
        List<BigDecimal> list3 = List.of(new BigDecimal("10.0"), new BigDecimal("23"), new BigDecimal("5"));
        list3.stream()
             .sorted(BigDecimalUtil::compare)
             .collect(Collectors.toList())
             .forEach(System.out::print);

        System.out.println();

        //3. 특정한 Object 의 Method Reference 사용
        boolean isMatch = Arrays.asList("a", "b", "c", "d", "e")
                .stream()
                .anyMatch("c"::equals);// "c" = 비교할 대상을 부여 :: equals = array 로 선언된 값들
        //.anyMatch(x -> "c".equals(x));

        System.out.println(isMatch);
    }

    private static void methodReference02() {
        /*
            First Class Function.
            Function can be passed as a parameter to another function.
            Using Lambda Expression.
         */
        String test1 = testFirstClassFunction1(3, i -> String.valueOf(i * 2));
        System.out.println(test1);

        /*
            Using Method Reference.
         */
        String test2 = testFirstClassFunction1(3, MethodReferenceExamples::doubleThenString);
        System.out.println(test2);
        /**
         * A function can be returned as the result of another function.
         */
        /*
            Using Lambda Expression
         */
        Function<Integer, String> fl = getDoubleThenToStringUsingLambdaExpression();
        String resultFl = fl.apply(3);
        System.out.println(resultFl);

        /*
            Using Method Reference
         */
        Function<Integer, String> fmr = getDoubleThenToStringUsingMethodReference();
        String resultFmr = fmr.apply(3);
        System.out.println(resultFmr);

        /**
         * A function can be stored in the data structure.
         */
        /*
            Using Lambda Expression.
         */
        List<Function<Integer, String>> fsL = Arrays.asList(i -> String.valueOf(i * 2), i -> String.valueOf(i * 3));
        for (Function<Integer, String> f : fsL) {
            String result = f.apply(3);
            System.out.println(result);
        }

        /*
            Using Method Reference
         */
        List<Function<Integer, String>> fsMr = Arrays.asList(MethodReferenceExamples::doubleThenString);
        fsMr.forEach(i -> System.out.println(i.apply(2)));


    }

    private static Function<Integer, String> getDoubleThenToStringUsingMethodReference() {
        return MethodReferenceExamples::doubleThenString;
    }

    private static Function<Integer, String> getDoubleThenToStringUsingLambdaExpression() {
        return i -> String.valueOf(i * 2);
    }

    private static String doubleThenString(int i) {
        return String.valueOf(i * 2);
    }

    private String intToString(int i) {
        return String.valueOf(i);
    }

    private static String testFirstClassFunction1(int n, Function<Integer, String> f) {
        return "The result is " + f.apply(n);
    }
}

class BigDecimalUtil {
    public static int compare(BigDecimal bd1, BigDecimal bd2) {
        return bd1.compareTo(bd2);
    }
}
