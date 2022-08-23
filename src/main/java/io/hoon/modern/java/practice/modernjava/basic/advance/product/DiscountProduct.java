package io.hoon.modern.java.practice.modernjava.basic.advance.product;

import lombok.ToString;

import java.math.BigDecimal;

@ToString(callSuper = true)
public class DiscountProduct extends Product {
    public DiscountProduct(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }
}
