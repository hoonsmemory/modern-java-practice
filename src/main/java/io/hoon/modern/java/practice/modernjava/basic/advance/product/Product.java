package io.hoon.modern.java.practice.modernjava.basic.advance.product;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class Product {

    private Long id;
    private String name;
    private BigDecimal price;
}
