package io.github.salestaxes.model.impl;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ImportedTaxTest {

    private ImportedTax tax;

    @Before
    public void setUp() {
        tax = new ImportedTax();
    }

    @Test
    public void testCalculateTaxRoundUp() {
        // Setup
        final BigDecimal price = new BigDecimal("47.50");
        final BigDecimal expectedResult = new BigDecimal("2.40");
        // Run the test
        final BigDecimal result = tax.calculateTax(price);
        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCalculateTaxRoundDown() {
        // Setup
        final BigDecimal price = new BigDecimal("47.49");
        final BigDecimal expectedResult = new BigDecimal("2.40");
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
}
