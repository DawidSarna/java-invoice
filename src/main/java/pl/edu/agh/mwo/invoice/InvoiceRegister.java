package pl.edu.agh.mwo.invoice;

public class InvoiceRegister {
    private final InvoiceToken invoiceToken;

    public InvoiceRegister() {
        this.invoiceToken = new InvoiceToken();
    }

    public Invoice createInvoice() {
        Invoice invoice = new Invoice(invoiceToken);
        invoiceToken.increaseInvoiceNumber();
        return invoice;
    }

    public class InvoiceToken {
        private int currentInvoiceNumber;

        private InvoiceToken() {
                currentInvoiceNumber = 1;
            }

            public int getCurrentInvoiceNumber() {
                return currentInvoiceNumber;
            }

            private void increaseInvoiceNumber() {
                currentInvoiceNumber++;
            }
        }
    }