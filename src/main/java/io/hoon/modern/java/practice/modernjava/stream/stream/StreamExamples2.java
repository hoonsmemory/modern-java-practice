package io.hoon.modern.java.practice.modernjava.stream.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 스트림은 lazy iterator 다.
 * filter(), map() 등 여러 메서드가 조합되어 있더라도 메서드 한줄씩 실행하지 않고,
 * 최종적으로 stream이 완성이 되었을 때 계산적으로 동작하므로 성능이 좋다.
 * 스트림을 사용하면 에러날 확률이 적고 브레이크가 없고 NullPointerException 에 신경 쓸 일이 없다.
 *
 * Intermediate Operation Method (중간처리 메서드)
 * Stream을 리턴하기 때문에 계속 Method Chaining을 통해서 무엇을 해야할 지 스트림에게 지시할 수 있다.
 * ex)filter(), map()..
 * 리턴 타입 : Stream
 *
 * Terminal Operation Method(최종처리 메서드)
 * 최종적으로 요청.
 * ex)  findFirst(), average()..
 * 리턴 타입 : Optional..
 */
public class StreamExamples2 {
    public static void main(String[] args) {
        //계산 후 가장 앞에 있는 인덱스 값 출력
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Integer result = null;
        for (Integer number : numbers) {
            if(number > 3 && number < 9) {
                Integer newNumber = number * 2;
                if(newNumber > 10) {
                    result = newNumber;
                    break;
                }
            }
        }
        //Imperative Result
        System.out.println("Imperative Result : " + result);

        Optional<Integer> first = numbers.stream()
                .filter(i -> {
                    System.out.println("i > 3");
                    return i > 3;
                })
                .filter(i -> {
                    System.out.println("i < 9");
                    return i < 9;
                })
                .map(i -> {
                    System.out.println("i * 2");
                    return i * 2;
                })
                .filter(i -> {
                    System.out.println("i > 10");
                    return i > 10;
                })
                .findFirst();
        //Functional Result
        System.out.println("Functional Result : "+ first.get());
    }
}
