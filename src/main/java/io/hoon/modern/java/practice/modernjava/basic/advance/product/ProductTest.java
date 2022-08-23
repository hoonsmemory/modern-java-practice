package io.hoon.modern.java.practice.modernjava.basic.advance.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ProductTest {

    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
           new Product(1L, "A", new BigDecimal("10.00")),
           new Product(2L, "B", new BigDecimal("55.50")),
           new Product(3L, "C", new BigDecimal("17.45")),
           new Product(4L, "D", new BigDecimal("23.00")),
           new Product(5L, "E", new BigDecimal("110.99"))
        );

        //20과 같거나 큰 금액을 가진 제품 모음
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if(product.getPrice().compareTo(new BigDecimal("20")) >= 0) {
                result.add(product);
            }
        }

        System.out.println(result);

        //20과 같거나 큰 금액을 가진 제품 모음
        Predicate<Product> predicate
                = product -> product.getPrice().compareTo(new BigDecimal("20")) >= 0;

        System.out.println(filter(products, predicate));


        //50보다 큰 제품에 대한 할인

        //1. 50보다 큰 제품으로 필터
        List<Product> expensiveProduct
                = filter(products, product -> product.getPrice().compareTo(new BigDecimal("50")) > 0);

        //2. 50보다 큰 제품 반값 할인
        Function<Product, DiscountProduct> divideFunction
                = product -> new DiscountProduct(product.getId(), product.getName(), product.getPrice().divide(new BigDecimal("2")));

        System.out.println(map(expensiveProduct, divideFunction));


        List<BigDecimal> prices = map(products, product -> product.getPrice());
        System.out.println(prices);


        //모든 상품 평균 값 구하기
        BigDecimal averagePrice = averagePrice(products, product -> product.getPrice());
        System.out.println(averagePrice);
    }

    private static <T> List<T> filter(List<T> list, Predicate<? super T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if(predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }

    private static <T> BigDecimal averagePrice(List<T> list, Function<T, BigDecimal> mapper) {
        BigDecimal total = BigDecimal.ZERO;
        int count = 0;
        for (T t : list) {
            total = total.add(mapper.apply(t));
            count++;
        }

        return total.divide(new BigDecimal(String.valueOf(count)));
    }
}
