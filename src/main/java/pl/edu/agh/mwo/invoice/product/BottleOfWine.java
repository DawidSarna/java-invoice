package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class BottleOfWine extends Product {

    public BottleOfWine(String name, BigDecimal price) {
        super(name, price, BigDecimal.ZERO, new BigDecimal("5.56"));
    }
}