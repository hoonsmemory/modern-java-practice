package io.hoon.modern.java.practice.modernjava.stream.stream;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExamples3 {

    public static void main(String[] args) {
        //List 구현
        List<Integer> list = Stream.of(1, 2, 3, 4, 5)
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .collect(Collectors.toList());

        System.out.println(list);

        //Set 구현
        Set<Integer> set = Stream.of(1, 3, 3, 3, 5)
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .collect(Collectors.toSet());

        System.out.println(set);

        //String 구현
        String string = Stream.of(1, 3, 3, 3, 5)
                .filter(i -> i > 2)
                .map(i ->  String.valueOf(i * 2))
                .distinct() // 중복 제거
                .collect(Collectors.joining(", ", "[", "]"));

        System.out.println(string);

        //Integer Cache : 기본적으로 -128 ~ 127까지는 캐시가 되어 있어 == 비교를 해도 문제가 없지만
        //                그 외의 숫자를 입력할 경우 equals() 로 반드시 비교해주어야 한다.
        Integer integer127 = 127;
        Optional<Integer> integerTest1 = Stream.of(1, 2, 3, 4, 5, 127, 128)
                .filter(i -> i == integer127)
                .findFirst();

        System.out.println(integerTest1.get());

        Integer integer128 = 128;
        Optional<Integer> integerTest2 = Stream.of(1, 2, 3, 4, 5, 127, 128)
                .filter(i -> i.equals(integer128))
                .findFirst();

        System.out.println(integerTest2.get());
    }
}
