package io.github.salestaxes.model.impl;

import io.github.salestaxes.model.Product;
import io.github.salestaxes.model.Purchase;
import lombok.*;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

@Builder
@EqualsAndHashCode
@Getter
@ToString
public class ConcretePurchase implements Purchase {

    private final static Logger LOG = Logger.getLogger(ConcretePurchase.class.getName());

    @NonNull
    private final Integer quantity;
    @NonNull
    private final Product product;

    @Override
    public BigDecimal getPurchasePrice() {
        final BigDecimal result = this.product.getUnitPrice().multiply(new BigDecimal(this.getQuantity()));
        LOG.log(Level.INFO, String.format("Purchase price: %s", result));
        return result;
    }
}
