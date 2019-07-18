package io.github.salestaxes.model;

import java.math.BigDecimal;

public interface Tax {

    /**
     * Calculate tax for given price
     *
     * @param price price
     * @return A {@link BigDecimal} tax for given price
     */
    public BigDecimal calculateTax(final BigDecimal price);

}
