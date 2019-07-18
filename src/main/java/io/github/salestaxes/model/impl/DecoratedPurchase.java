package io.github.salestaxes.model.impl;

import io.github.salestaxes.model.Product;
import io.github.salestaxes.model.Purchase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DecoratedPurchase extends ConcretePurchase {

    private final static Logger LOG = Logger.getLogger(DecoratedPurchase.class.getName());

    @NonNull
    private final BigDecimal tax;

    DecoratedPurchase(@NonNull Integer quantity,
                      @NonNull Product product,
                      @NonNull BigDecimal tax) {
        super(quantity, product);
        this.tax = tax;
        LOG.log(Level.INFO, String.format("Decorated purchase created: %s", this.toString()));
    }

    /**
     * Add tax to a purchase
     * @param purchase Purchease to decorate
     * @param tax      tax to add
     * @return a {@link Purchase} Decorated purchase with tax
     */
    public static Purchase decorate(@NonNull final Purchase purchase,
                                    @NonNull final BigDecimal tax) {
        LOG.log(Level.INFO, String.format("Starting decorating purchase %s with tax: %s", purchase, tax));
        return new DecoratedPurchase(
                purchase.getQuantity(),
                purchase.getProduct(),
                tax);
    }

    @Override
    public BigDecimal getPurchasePrice() {
        final BigDecimal result = super.getPurchasePrice().add(getTax());
        LOG.log(Level.INFO, String.format("Decorated purchase price: %s", result));
        return result;
    }

}
