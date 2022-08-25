package io.hoon.modern.java.practice.modernjava.methodreference;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.function.Function;

public class MethodReferenceExamples2Constructor {
    public static void main(String[] args) {
        //일반적으로 인스턴스 생성
        final Section section1 = new Section(1);
        System.out.println(section1);

        //Function 을 활용해서 인스턴스 생성 (LambdaExpression)
        final Function<Integer, Section> sectionFactoryWithLambdaExpression = i -> new Section(i);
        final Section section1WithLambdaExpression = sectionFactoryWithLambdaExpression.apply(1);
        System.out.println(section1WithLambdaExpression);

        //Function 을 활용해서 인스턴스 생성 (MethodReference)
        final Function<Integer, Section> sectionFactoryWithMethodReference = Section::new;
        final Section section1WithMethodReference = sectionFactoryWithMethodReference.apply(1);
        System.out.println(section1WithMethodReference);

        System.out.println("=======================================================");
        final Product product1 = new ProductA(1L, "A", new BigDecimal("100"));
        System.out.println(product1);

        final ProductCreator productCreator = ProductA::new;
        Product product2 = productCreator.create(1L, "chicken", new BigDecimal("15.59"));
        System.out.println(product2);

        System.out.println("=======================================================");

        final ProductA a1
                = createProduct(1L, "pizza", new BigDecimal("9.99"), (id, name, price) -> new ProductA(id, name, price));
        System.out.println(a1);

        final ProductA a2
                = createProduct(2L, "chocolate", new BigDecimal("2.50"), ProductA::new);
        System.out.println(a2);

        final Product b1
                = createProduct(1L, "MacbookAir", new BigDecimal("99.99"), ProductB::new);
        System.out.println(b1);
    }

    //동일한 과정을 거치고 같은 타입을 상속 받지만 다른 객체를 가질 때 사용
    private static <T extends Product> T createProduct(final Long id,
                                                final String name,
                                                final BigDecimal price,
                                                final ProductCreator<T> productCreator) {
        if(id == null || id < 1L) {
            throw new IllegalArgumentException("The id must be a positive Long.");
        }
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The name is not given");
        }
        if(price == null || price.compareTo(BigDecimal.ZERO) <= 0) { // price <= ZERO
            throw new IllegalArgumentException("The price must be greater then 0");
        }
        return productCreator.create(id, name, price);
    }
}

//Function Custom
@FunctionalInterface
interface ProductCreator<T extends Product> {
    T create(Long id, String name, BigDecimal price);
}

@AllArgsConstructor
@Data
class Section {
    private int number;
}

class ProductA extends Product {
    public ProductA(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }

    @Override
    public String toString() {
        return "A =" + super.toString();
    }
}

class ProductB extends Product {
    public ProductB(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }

    @Override
    public String toString() {
        return "B =" + super.toString();
    }
}

@AllArgsConstructor
@Data
abstract class Product {
    private Long id;
    private String name;
    private BigDecimal price;
}
