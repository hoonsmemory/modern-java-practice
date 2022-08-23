package io.hoon.modern.java.practice.modernjava.basic.advance.order;

import io.hoon.modern.java.practice.modernjava.basic.advance.product.Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class OrderTest {

    public static void main(String[] args) {
        Product productA = new Product(1L, "A", new BigDecimal("10.00"));
        Product productB = new Product(2L, "B", new BigDecimal("55.50"));
        Product productC = new Product(3L, "C", new BigDecimal("17.45"));
        Product productD = new Product(4L, "D", new BigDecimal("23.00"));
        Product productE = new Product(5L, "E", new BigDecimal("110.99"));


        Order order = new Order(1L, "A-123", Arrays.asList(
                new OrderItem(1L, productA, 10),
                new OrderItem(2L, productB, 20),
                new OrderItem(3L, productC, 30),
                new OrderItem(4L, productD, 40),
                new OrderItem(5L, productE, 50)));

        System.out.println(order.totalPrice());
    }
}
