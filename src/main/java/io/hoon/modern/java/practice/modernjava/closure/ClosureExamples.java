package io.hoon.modern.java.practice.modernjava.closure;

/**
 * Closure
 * int i = 100;
 * someMethod(x -> (x * 2) + i);
 * someMethod 에서 i를 사용하면서 scope 를 확장했으므로(closure over) closure 라고 부른다.
 *
 * 특징1
 * int i == Effectively Final
 * 만약 위처럼 someMethod 에서 i를 사용할 경우 Effectively Final 가 되어 어디서도 i의 값을 수정을 할 수 없다.
 * 멀티 스레드 환경에서 어떻게 사용할 지 모르기 때문에 막아두었다.(Race Condition)
 */
public class ClosureExamples {

    private int number = 999;

    public static void main(String[] args) {
        new ClosureExamples().test2();
    }

    //특징 4
    //Anonymous Class : Field Shadowing({int number = 50;} 선언 가능..)
    //Lambda Expression : Enclosing Class (scope 확장으로 {number = 50;} 선언 불가..)
    private void test3() {
        //Effectively Final
        int number = 50;

        testClosure("Anonymous Class",new Runnable() {
            @Override
            public void run() {
                int number = 50;
                System.out.println(number);
            }
        });

        testClosure("Lambda Expression", () -> {
            //variable number is already defined in method test3()
            //int number = 50;
            System.out.println(number);
            System.out.println(this.number);
        });
    }

    //특징 3) Anonymous Class : Method Shadowing
    private void test2() {
        testClosure("Anonymous Class",new Runnable() {
            @Override
            public void run() {
                /*
                    Anonymous Class 가 가지고 있는 메서드(상속한 메서드 포함)와
                    이름이 동일한 외부 메서드에 접근할 경우 shadowing 이 발생한다.
                    System.out.println(this.toString("value"));
                 */
                System.out.println(ClosureExamples.toString("value"));
            }
        });

        testClosure("Lambda Expression", () -> System.out.println(toString("value")));
    }

    public static <T> String toString(T value) {
        return "The value is " + String.valueOf(value) +".";
    }


    //특징2) Anonymous Class 는 ClosureExamples 에 선언된 필드 this.number 를 통해 불러올 수 없는 문제
    private void test1() {
        //Effectively Final
        int number = 100;

        testClosure("Anonymous Class",new Runnable() {
            @Override
            public void run() {
                /*
                    Anonymous Class 기준(Lambda Expression 과는 관련 없다.)
                    test1()에 로컬 변수로 number 를 가지고 있고, 메서드 밖 필드값으로 number 가 있다고 가정한다.
                    this.number 를 통해 필드로 선언된 number 를 불러올 수 없다.
                    그 이유는 현재 메서드는 new Runnable()안에 있고,
                    this 를 사용했을 경우 Runnable 인스턴스 안에 있는 this.number 를 참조하기 때문이다. (Runnable 인스턴스에는 number 필드가 없다.)
                    ClosureExamples.this.number 는 가능하다.
                */
                System.out.println(number);
                System.out.println(this);

                //지저분하게 ClosureExamples 정보 가져오기
                System.out.println(ClosureExamples.this.number);
                System.out.println(ClosureExamples.this);
            }
        });

        /*
            Lambda Expression 에서 만약 this.number 를 사용했다면 ClosureExamples 클래스의 필드값을 가져온다. (scope 가 없기 때문에)
            this.toString()을 할 경우 ClosureExamples 클래스에서 정의된 toString()가 실행된다.
         */
        testClosure("Lambda Expression", () -> System.out.println(this.number + "\n" + this));
    }

    private static void testClosure(final String name, final Runnable runnable) {
        System.out.println("===============================");
        System.out.println(name + " : ");
        runnable.run();
    }

    @Override
    public String toString() {
        return "ClosureExamples{" +
                "number=" + number +
                '}';
    }
}
