package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class ProductTest {
    @Test
    public void testProductNameIsCorrect() {
        Product product = new OtherProduct("buty", new BigDecimal("100.0"));
        Assert.assertEquals("buty", product.getName());
    }

    @Test
    public void testProductPriceAndTaxWithDefaultTax() {
        Product product = new OtherProduct("Ogorki", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("100"), Matchers.comparesEqualTo(product.getPrice()));
        Assert.assertThat(new BigDecimal("0.23"), Matchers.comparesEqualTo(product.getTaxPercent()));
    }

    @Test
    public void testProductPriceAndTaxWithDairyProduct() {
        Product product = new DairyProduct("Szarlotka", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("100"), Matchers.comparesEqualTo(product.getPrice()));
        Assert.assertThat(new BigDecimal("0.08"), Matchers.comparesEqualTo(product.getTaxPercent()));
    }

    @Test
    public void testPriceWithTax() {
        Product product = new DairyProduct("Oscypek", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("108"), Matchers.comparesEqualTo(product.getPriceWithTax()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNullName() {
        new OtherProduct(null, new BigDecimal("100.0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithEmptyName() {
        new TaxFreeProduct("", new BigDecimal("100.0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNullPrice() {
        new DairyProduct("Banany", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNegativePrice() {
        new TaxFreeProduct("Mandarynki", new BigDecimal("-1.00"));
    }

    @Test
    public void wineFlatPrice(){
        Product p = new BottleOfWine("Martini", new BigDecimal("30.00"));
        Assert.assertThat(new BigDecimal("30.00"), Matchers.comparesEqualTo(p.getPrice()));
    }

    @Test
    public void wineWithTax(){
        Product p = new BottleOfWine("Martini", new BigDecimal("30.00"));
        Assert.assertThat(new BigDecimal("35.56"), Matchers.equalTo(p.getPriceWithTax()));
    }

    @Test
    public void fuelCarnisterFlatPrice(){
        Product p = new FuelCanister("Kanister PB95", new BigDecimal("180"));
        Assert.assertThat(new BigDecimal("180"), Matchers.comparesEqualTo(p.getPrice()));
    }

    @Test
    public void fuelCarnisterWithTax(){
        Product p = new FuelCanister("Kanister PB95", new BigDecimal("180"));
        Assert.assertThat(new BigDecimal("185.56"), Matchers.equalTo(p.getPriceWithTax()));
    }
}