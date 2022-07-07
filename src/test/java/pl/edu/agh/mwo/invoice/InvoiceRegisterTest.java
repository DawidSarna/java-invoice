package pl.edu.agh.mwo.invoice;

import org.junit.Test;

import pl.edu.agh.mwo.invoice.product.FuelCanister;
import pl.edu.agh.mwo.invoice.product.Product;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class InvoiceRegisterTest {
    @Test
    public void firstInvoiceRegister() {
        InvoiceRegister invoiceRegister = new InvoiceRegister();
        Invoice invoice = invoiceRegister.createInvoice();
        assertEquals(1, invoice.getInvoiceNumber());
    }

    @Test
    public void fuelCanisterCreatedOnCarpenterDay () {
        Invoice invoice = new Invoice(new InvoiceRegister().getInvoiceToken(), LocalDate.of(2020,3,19));
        Product product = new FuelCanister("Kanister PB95", new BigDecimal("180.00"));
        invoice.addProduct(product, 1);
        Product product0 = (Product) invoice.getProducts().keySet().toArray()[0];
        assertEquals(new BigDecimal("180.00"), product0.getPriceWithTax());
    }

    @Test
    public void secondRegisterBiggerByThanFirst() {
        InvoiceRegister invoiceRegister = new InvoiceRegister();
        Invoice invoice1 = invoiceRegister.createInvoice();
        Invoice invoice2 = invoiceRegister.createInvoice();
        assertEquals(1, invoice2.getInvoiceNumber() - invoice1.getInvoiceNumber());
    }
}