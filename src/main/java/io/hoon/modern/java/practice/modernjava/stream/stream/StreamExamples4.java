package io.hoon.modern.java.practice.modernjava.stream.stream;

import io.hoon.modern.java.practice.modernjava.basic.advance.product.Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.*;

/**
 * reduce() : T reduce(T identity, BinaryOperator<T> accumulator)
 * identity : 초기값( 계산하기 앞서 초기화할 값)
 * accumulator : function 으로 2개의 파라미터를 가지고 있다.(arity 2)
 *               첫 번째 파라미터 : 이전 값(처음엔 초기값), 두 번째 파라미터 : 새로 받은 값
 * return : 같은 타입의 값(최종적으로 계산한 값)
 *
 */
public class StreamExamples4 {

    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product(1L, "A", new BigDecimal("100.50")),
                new Product(2L, "B", new BigDecimal("23.00")),
                new Product(3L, "C", new BigDecimal("31.45")),
                new Product(4L, "D", new BigDecimal("80.20")),
                new Product(5L, "E", new BigDecimal("7.50"))
        );

        //30보다 큰 값 List로 변환
        List<Product> productList = products.stream()
                .filter(i -> i.getPrice().compareTo(new BigDecimal("30")) >= 0)
                .collect(toList());
        System.out.println("[List]");
        System.out.println(productList);

        //30보다 큰 값 String으로 변환
        String productString = products.stream()
                .filter(i -> i.getPrice().compareTo(new BigDecimal("30")) >= 0)
                .map(i -> String.valueOf(i))
                .collect(joining("\n"));
        System.out.println();
        System.out.println("[String]");
        System.out.println(productString);

        //값 전체 합
        BigDecimal addResult = products.stream()
                //product 가 갖고 있는 값 중에서 계산에 필요한 price 만 반환한다.
                .map(product -> product.getPrice())
                // Element 를 하나씩 줄여나가면서 price 의 값을 합한다.
                .reduce(BigDecimal.ZERO, (product1, product2) -> product1.add(product2));

        System.out.println();
        System.out.println("[price 전체 합하기]");
        System.out.println(addResult);

        //값 전체 합 (코드의 복잡성으로 권장하지 않는 예)
        BigDecimal addResult2 = products.stream()
                .reduce(BigDecimal.ZERO, (price, product) -> price.add(product.getPrice()),
                                         (price1, price2) -> price1.add(price2));

        System.out.println();
        System.out.println("[price 전체 합하기]");
        System.out.println(addResult2);


        //
    }
}
