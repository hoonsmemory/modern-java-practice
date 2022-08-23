package io.hoon.modern.java.practice.modernjava.basic.advance.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
@Data
public class Order {
    private Long id;
    private String orderNumber;
    private List<OrderItem> items;

    public BigDecimal totalPrice() {
        return total(items, item -> item.getItemTotal());
    }

    private static <T> BigDecimal total(List<T> list, Function<T, BigDecimal> mapper) {
        BigDecimal total = BigDecimal.ZERO;
        int count = 0;
        for (T t : list) {
            total = total.add(mapper.apply(t));
            count++;
        }

        return total;
    }
}
