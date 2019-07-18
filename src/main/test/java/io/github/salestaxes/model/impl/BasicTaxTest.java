package io.github.salestaxes.model.impl;

import io.github.salestaxes.model.Product;
import io.github.salestaxes.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.*;

public class BasicTaxTest {

    private BasicTax tax;

    @Before
    public void setUp() {
        tax = new BasicTax();
    }

    @Test
    public void testCalculateTaxRoundUp() {
        // Setup
        final BigDecimal price = new BigDecimal("14.99");
        final BigDecimal expectedResult = new BigDecimal("1.50");
        // Run the test
        final BigDecimal result = tax.calculateTax(price);
        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCalculateTaxRoundDown() {
        // Setup
        final BigDecimal price = new BigDecimal("14.44");
        final BigDecimal expectedResult = new BigDecimal("1.45");
        // Run the test
        final BigDecimal result = tax.calculateTax(price);
        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCalculateTaxPriceIs0() {
        // Setup
        final BigDecimal price = new BigDecimal("0.00");
        final BigDecimal expectedResult = new BigDecimal("0.00");
        // Run the test
        final BigDecimal result = tax.calculateTax(price);
        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testIsBasicTaxApplicableWithApplicableProduct() {
        // Setup
        final Map<String, String> categories = TestUtils.populateExpectedCategoryMap();
        final Product product = ConcreteProduct.builder()
                .imported(false)
                .unitPrice(new BigDecimal("14.99"))
                .name("music CD")
                .build();
        // Run the test
        final Boolean result = BasicTax.isBasicTaxApplicable(categories, product);
        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testIsBasicTaxApplicableWithApplicableProductImported() {
        // Setup
        final Map<String, String> categories = TestUtils.populateExpectedCategoryMap();
        final Product product = ConcreteProduct.builder()
                .imported(true)
                .unitPrice(new BigDecimal("14.99"))
                .name("imported music CD")
                .build();
        // Run the test
        final Boolean result = BasicTax.isBasicTaxApplicable(categories, product);
        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testIsBasicTaxApplicableWithNoApplicableProduct() {
        // Setup
        final Map<String, String> categories = TestUtils.populateExpectedCategoryMap();
        final Product product = ConcreteProduct.builder()
                .imported(false)
                .unitPrice(new BigDecimal("14.99"))
                .name("book")
                .build();
        // Run the test
        final Boolean result = BasicTax.isBasicTaxApplicable(categories, product);
        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testIsBasicTaxApplicableWithNoApplicableProductImported() {
        // Setup
        final Map<String, String> categories = TestUtils.populateExpectedCategoryMap();
        final Product product = ConcreteProduct.builder()
                .imported(true)
                .unitPrice(new BigDecimal("14.99"))
                .name("book")
                .build();
        // Run the test
        final Boolean result = BasicTax.isBasicTaxApplicable(categories, product);
        // Verify the results
        assertFalse(result);
    }
}
