package io.hoon.modern.java.practice.modernjava.stream.identity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class IdentifyTest {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        System.out.println(mapOld1(list, i -> i * 2));
        System.out.println(mapOld1(list, null));

        System.out.println();

        System.out.println(mapOld2(list, i -> i * 2));
        System.out.println(mapOld2(list, null));

        System.out.println();

        System.out.println(map(list, i -> i * 2));
        System.out.println(map(list, Function.identity()));
    }

    private static <T, R> List<R> map(final List<T> list, final Function<T, R> mapper) {
        final List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(mapper.apply(t));
        }

        return result;
    }

    private static <T, R> List<R> mapOld2(final List<T> list, final Function<T, R> mapper) {
        final Function<T, R> function;
        if(mapper != null) {
            function = mapper;
        } else {
            function = t -> (R) t;
        }

        final List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(function.apply(t));
        }

        return result;
    }

    private static <T, R> List<R> mapOld1(final List<T> list, final Function<T, R> mapper) {
        final List<R> result;
        if(mapper != null) {
            result = new ArrayList<>();
        } else {
            result = new ArrayList<>((List<R>) list);
        }

        if(result.isEmpty()) {
            for (T t : list) {
                result.add(mapper.apply(t));
            }
        }
        return result;
    }
}
