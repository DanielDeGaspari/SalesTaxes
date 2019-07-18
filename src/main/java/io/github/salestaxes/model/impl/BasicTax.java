package io.github.salestaxes.model.impl;

import io.github.salestaxes.Utils.Constants;
import io.github.salestaxes.Utils.FormatUtils;
import io.github.salestaxes.model.Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.github.salestaxes.Utils.Constants.BASIC_TAX;

public class BasicTax extends AbstractTax {

    private final static Logger LOG = Logger.getLogger(BasicTax.class.getName());

    final private BigDecimal basicTax = new BigDecimal(BASIC_TAX);

    @Override
    public BigDecimal calculateTaxBeforeRound(final BigDecimal price) {
        LOG.log(Level.INFO, String.format("Calculate tax before round for price: %s", price));
        BigDecimal beforeRoundPrice = price.multiply(basicTax);
        BigDecimal result = beforeRoundPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        LOG.log(Level.INFO, String.format("Tax before round = %s", result));
        return result;
    }

    /**
     * Check if tax is applicable for given product
     *
     * @param categories product's categories
     * @param product    product
     * @return A {@link Boolean} return true only if basic tax is applicable
     */
    public static Boolean isBasicTaxApplicable(final Map<String, String> categories, final Product product) {
        LOG.log(Level.INFO, String.format("Check if basic tax is applicable for product %s with categories: %s", product, categories));
        final List<String> excluded = Arrays.asList(Constants.EXCLUDED_CATEGORIES);
        final String productName = FormatUtils.slugify(product.getName());
        final String category = categories.get(productName);
        final Boolean result = !excluded.contains(category);
        LOG.log(Level.INFO, String.format("Applicable : %s for Product name: %s, category: %s", result, productName, category));
        return result;
    }

}
