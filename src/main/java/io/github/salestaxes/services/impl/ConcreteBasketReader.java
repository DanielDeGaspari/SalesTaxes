package io.github.salestaxes.services.impl;

import io.github.salestaxes.Utils.Constants;
import io.github.salestaxes.model.Basket;
import io.github.salestaxes.model.Product;
import io.github.salestaxes.model.Purchase;
import io.github.salestaxes.model.impl.ConcreteBasket;
import io.github.salestaxes.model.impl.ConcreteProduct;
import io.github.salestaxes.model.impl.ConcretePurchase;
import io.github.salestaxes.services.BasketReader;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConcreteBasketReader implements BasketReader {

    private final static Logger LOG = Logger.getLogger(ConcreteBasketReader.class.getName());

    @Override
    public Basket parse(final Stream<String> stringStream) {
        LOG.log(Level.INFO, "Starting parsing basket");
        final Basket basket = new ConcreteBasket();
        // Read only non-empty lines and create a list
        final List<String> lines = stringStream.filter(line -> !Objects.equals(StringUtils.EMPTY, line)) // Check if line is empty
                .collect(Collectors.toList());
        // Parse lines and add purchases to basket
        LOG.log(Level.INFO, "Starting parsing each line");
        lines.forEach(line -> {
            LOG.log(Level.INFO, String.format("Parsing line: %s", line));
            final Matcher matcher = Constants.OUTPUT_TEXT_PATTERN.matcher(line);
            try {
                // Prepare purchase
                final Purchase purchase = createPurchaseFromLine(matcher);
                LOG.log(Level.INFO, String.format("Line parsed: purchase %s was created", purchase));
                // Add purchase to basket
                basket.addPurchase(purchase);
                LOG.log(Level.INFO, String.format("Purchase %s added to shopping basket", purchase));
            } catch (Exception e) {
                LOG.log(Level.SEVERE, String.format("Unable to create Purchase for: %s: wrong line's pattern", line));
            }
        });
        LOG.log(Level.INFO, String.format("Basket created %s", basket));
        return basket;
    }

    /**
     * Create purchase from a given matcher representing current line
     *
     * @param matcher matcher for current line
     * @return A {@link Purchase} Purchase object representing current line
     * @throws Exception occurs when there are some error parsing line
     */
    private Purchase createPurchaseFromLine(final Matcher matcher) throws Exception {
        LOG.log(Level.INFO, "Trying to create purchase from current line");
        if (matcher.find()) {
            // Parse properties
            final Integer quantity = Integer.parseInt(matcher.group(Constants.QUANTITY));
            LOG.log(Level.INFO, String.format("Quantity: %s", quantity));
            Boolean imported = false;
            String name = matcher.group(Constants.NAME);
            // name contains imported
            if (name.contains(Constants.IMPORTED)) {
                LOG.log(Level.INFO, String.format("Name %s contains 'imported'", name));
                name = name.replaceAll(Constants.IMPORTED, StringUtils.EMPTY)
                        .replaceAll("\\s{2,}", " ")
                        .trim();
                imported = true;
            }
            LOG.log(Level.INFO, String.format("Name: %s", name));
            LOG.log(Level.INFO, String.format("Imported: %s", imported));
            final BigDecimal unitPrice = new BigDecimal(matcher.group(Constants.UNIT_PRICE));
            LOG.log(Level.INFO, String.format("Unit price: %s", unitPrice));
            // Create product
            LOG.log(Level.INFO, "Starting building product");
            final Product product = ConcreteProduct.builder()
                    .name(name)
                    .unitPrice(unitPrice)
                    .imported(imported)
                    .build();
            LOG.log(Level.INFO, String.format("Product builded: %s", product));
            // Create purchase
            final Purchase purchase = ConcretePurchase.builder()
                    .product(product)
                    .quantity(quantity)
                    .build();
            LOG.log(Level.INFO, String.format("Purchase builded: %s", purchase));
            return purchase;
        } else {
            LOG.log(Level.WARNING, "Error parsing line - unable to create purchase");
            throw new Exception();
        }
    }

}
