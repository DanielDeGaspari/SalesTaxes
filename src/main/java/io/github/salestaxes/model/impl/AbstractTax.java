package io.github.salestaxes.model.impl;

import io.github.salestaxes.model.Tax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractTax implements Tax {

    private final static Logger LOG = Logger.getLogger(AbstractTax.class.getName());

    final static BigDecimal DIVISOR = new BigDecimal("0.05");
    final static BigDecimal MULTIPLICAND = new BigDecimal("0.05");

    @Override
    public BigDecimal calculateTax(final BigDecimal price) {
        // Using template method pattern for tax calculus
        LOG.log(Level.INFO, String.format("Calculating tax for price: %s", price));
        final BigDecimal taxBeforeRound = this.calculateTaxBeforeRound(price);
        LOG.log(Level.INFO, String.format("Tax before round: %s", taxBeforeRound));
        final BigDecimal taxAfterRound = taxBeforeRound.divide(DIVISOR, 0, RoundingMode.UP).multiply(MULTIPLICAND);
        LOG.log(Level.INFO, String.format("Tax after round: %s", taxAfterRound));
        return taxAfterRound;
    }

    /**
     * Calculate tax for given price, before round
     *
     * @param price price that should be rounded
     * @return A {@link BigDecimal} tax before round for given price
     */
    public abstract BigDecimal calculateTaxBeforeRound(final BigDecimal price);
}
