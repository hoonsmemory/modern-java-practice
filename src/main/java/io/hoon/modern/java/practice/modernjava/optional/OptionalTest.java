package io.hoon.modern.java.practice.modernjava.optional;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {
        Integer value = null;



    }

    //7. 전달 받은 함수를 실행해서값을 변환한 Optional로 리턴
    //map은 값이 없으면 빈 Optional을 리턴
    private static void extracted(Member member) {

        //방법 1
        Optional<Member> memOpt = Optional.ofNullable(member);
        Optional<LocalDate> birtOpt = memOpt.map(m -> m.getBirthday());
        Optional<String> pdOpt1 = birtOpt.map(b -> localDateToString(b));

        //방법 2
        Optional<String> pdOpt2 = memOpt.map(m -> m.getBirthday())
                                        .map(b -> localDateToString(b));
    }

    private static String localDateToString(LocalDate b) {
        return b.toString();
    }

    //6. 값이 있다면 값을 리턴, 값이 없다면 익셉션(orElseThrow)
    private static void test6(Integer value) {
        Optional<Integer> opt = Optional.ofNullable(value);
        Integer result = opt.orElseThrow(() -> new NoSuchElementException());
    }

    //5. 값이 없을 떄 다른 값 사용(orElse, orElseGet, or)
    //String value = "test";
    //String result = value == null ? "default" : value;
    private static void test5(Integer value) {

        Optional<Integer> opt = Optional.ofNullable(value);
        Integer result1 = opt.orElse(0);
        Integer result2 = opt.orElseGet(() -> 0);
        Optional<Integer> result3 = opt.or(() -> Optional.of(0));
    }

    //4. 값이 있을 경우 실행 (ifPresent, ifPresentOrElse)
    private static void test4(int value) {
        Optional<Integer> opt = Optional.of(value);
        opt.ifPresent(v -> sum10(v));

        Optional<Integer> opt1 = Optional.ofNullable(value);
        opt1.ifPresentOrElse(v -> sum10(v),
                () -> get10());
    }

    private static int sum10(int value) {
        value = value + 10;
        System.out.println(value);
        return value;
    }

    private static int get10() {
        System.out.println(10);
        return 10;
    }


    //3. 값이 있는지 없는지 확인(isPresent)
    private static void test3(String value) {
        Optional<String> opt = Optional.of(value);
        opt.isPresent();
        opt.isEmpty();

        Optional<Object> opt1 = Optional.empty();
        opt1.isPresent();
        opt1.isEmpty();
    }

    //2. 값 구하기(get)
    private static void test2(String value) {
        Optional<String> opt = Optional.of(value);

        String getData = opt.get();
        System.out.println(getData);

        Optional.empty().get(); //NoSuchElementException 발생
    }

    //1. Optional 생성 방법(of, ofNullable, empty)
    private static Optional<String> test1(String value) {
        Optional<String> opt = Optional.of(value); //값이 없을 경우 NullPointerException 발생
        System.out.println(opt);

        Optional<String> opt1 = Optional.ofNullable(value); //null일 경우 Optional.empty
        System.out.println(opt1);

        Optional<Object> opt2 = Optional.empty();
        System.out.println(opt2); // 빈 값으로 생성
        return opt;
    }
}
