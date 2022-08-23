package io.hoon.modern.java.practice.modernjava.basic.advance.order;

import io.hoon.modern.java.practice.modernjava.basic.advance.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class OrderItem {
    private Long id;
    private Product product;
    private int quantity;

    public BigDecimal getItemTotal() {
        return product.getPrice().multiply(new BigDecimal(quantity));
    }
}
