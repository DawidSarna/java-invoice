package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.FuelCanister;
import pl.edu.agh.mwo.invoice.product.Product;
import pl.edu.agh.mwo.invoice.product.TaxFreeProduct;

public class Invoice {
    private final LocalDate date;
    private Map<Product, Integer> products;
    private int invoiceNumber;

    public Invoice(InvoiceRegister.InvoiceToken invoiceToken, LocalDate date) {
        products = new LinkedHashMap<>();
        this.invoiceNumber = invoiceToken.getCurrentInvoiceNumber();
        this.date = date;
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        if (product instanceof FuelCanister && new CarpenterDay().isCarpenterDay(date)) {
            product = new TaxFreeProduct(product.getName(), product.getPrice());
        }
        if (products.containsKey(product)) {
            products.put(product, products.get(product) + quantity);
        } else {
            products.put(product, quantity);
        }
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append(invoiceNumber + "\n");
        products.entrySet().stream().forEach(item -> sb.append(
                item.getKey().getName() + " ilosc:" + item.getValue() + " cena:" + item.getKey().getPrice().toString() + "\n"));
        sb.append("Ilosc unikalnych produktow: " + products.size());
        return sb.toString();
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }
}