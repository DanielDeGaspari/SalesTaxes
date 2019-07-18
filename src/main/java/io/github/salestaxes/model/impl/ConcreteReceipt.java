package io.github.salestaxes.model.impl;

import io.github.salestaxes.model.Purchase;
import io.github.salestaxes.model.Receipt;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@EqualsAndHashCode
@Getter
@ToString
public class ConcreteReceipt implements Receipt {

    private final static Logger LOG = Logger.getLogger(ConcreteReceipt.class.getName());

    private List<Purchase> purchases;

    public ConcreteReceipt() {
        this.purchases = new ArrayList<>();
    }

    @Override
    public void addPurchase(final Purchase purchase) {
        LOG.log(Level.INFO, String.format("Adding to receipt purchase: %s", purchase));
        this.purchases.add(purchase);
    }

    @Override
    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (final Purchase p : purchases) {
            total = total.add(p.getPurchasePrice());
        }
        LOG.log(Level.INFO, String.format("Total price calculated: %s", total));
        return total;
    }

    @Override
    public BigDecimal getTotalTax() {
        BigDecimal total = BigDecimal.ZERO;
        for (final Purchase p : purchases) {
            if (p instanceof DecoratedPurchase) {
                total = total.add(((DecoratedPurchase) p).getTax());
            }
        }
        LOG.log(Level.INFO, String.format("Total tax calculated: %s", total));
        return total;
    }
}

