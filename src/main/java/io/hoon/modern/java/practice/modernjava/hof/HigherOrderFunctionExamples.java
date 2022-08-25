package io.hoon.modern.java.practice.modernjava.hof;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Higher-Order-Function(고계함수 혹은 고차함수)
 * 함수를 값처럼 넘길 수 있다.
 * Function 이 두가지 조건 중 한가지 이상을 만족할 경우.
 * 1. 파라미터로 받는 경우 : functionA(functionB()){}
 * 2. 리턴값으로 받는 경우 : functionA() = functionB();
 *
 * functionA<Integer, functionB<Integer, Integer>>
 * f = i -> i2 -> i + i2
 * functionA : i ->
 * functionB : i2 -> i + i2
 *
 */
public class HigherOrderFunctionExamples {

    public static void main(String[] args) {
        //1. 파라미터로 받는 경우
        final Function<Function<Integer, String>, String> f = g -> g.apply(10);
        System.out.println(f.apply(i -> "#" + i));

        //2. 리턴값으로 받는 경우
        final Function<Integer, Function<Integer, Integer>> f2 = i -> (i2 -> i + i2);
        System.out.println(f2.apply(1).apply(9));


        final List<Integer> list = List.of(1, 2, 3, 4, 5);

        //map 만들어서 테스트
        List<String> hofList = map(list, i -> "#" + i);
        System.out.println(hofList);

        //stream 이용해서 테스트
        List<String> streamList = list.stream()
                .map(i -> "#" + i)
                .collect(Collectors.toList());

        System.out.println(streamList);

        //hof querying
        Function<Integer, Function<Integer, Function<Integer, Integer>>> hof = i1 -> i2 -> i3 -> i1 + i2 + i3;

        //재사용성 function 을 구현하여 언제든 가져다 쓸 수 있다.
        final Function<Integer, Function<Integer, Integer>> plus10 = hof.apply(10);
        Integer sum = plus10.apply(2).apply(3);

        System.out.println(sum);
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        final List<R> result = new ArrayList<>();

        for (T t : list) {
            result.add(mapper.apply(t));
        }

        return result;
    }
}
