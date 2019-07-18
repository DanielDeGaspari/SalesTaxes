package io.github.salestaxes.model.impl;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.github.salestaxes.Utils.Constants.IMPORTED_TAX;

public class ImportedTax extends AbstractTax {

    private final static Logger LOG = Logger.getLogger(ImportedTax.class.getName());

    final private BigDecimal imported = new BigDecimal(IMPORTED_TAX);

    @Override
    public BigDecimal calculateTaxBeforeRound(final BigDecimal price) {
        LOG.log(Level.INFO, String.format("Calculating tax before round for imported product with price %s", price));
        BigDecimal beforeRoundPrice = price.multiply(imported);
        final BigDecimal result = beforeRoundPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        LOG.log(Level.INFO, String.format("Tax before round for imported product for price %s", result));
        return result;
    }
}
