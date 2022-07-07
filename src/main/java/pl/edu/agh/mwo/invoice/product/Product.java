package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Product {
    private final String name;

    private final BigDecimal price;

    private final BigDecimal taxPercent;
    private final BigDecimal taxValue;

    protected Product(String name, BigDecimal price, BigDecimal taxPercent, BigDecimal taxValue) {
        if (name == null || name.equals("") || price == null || taxPercent == null
                || taxPercent.compareTo(new BigDecimal(0)) < 0
                || price.compareTo(new BigDecimal(0)) < 0) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.price = price;
        this.taxPercent = taxPercent;
        this.taxValue = taxValue;

        if ((price == null) || (price.compareTo(new BigDecimal(0)) == -1)) {
            throw new IllegalArgumentException();
        }
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getTaxPercent() {
        return taxPercent;
    }

    public BigDecimal getPriceWithTax() {
        if (taxPercent.equals(BigDecimal.ZERO)) {
            return price.add(taxValue);
        } else {
            return price.multiply(taxPercent).add(price);
        }
    }

    public BigDecimal getTaxValue() {
        return taxValue;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Product product = (Product) object;
        return name.equals(product.name) && price.equals(product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}