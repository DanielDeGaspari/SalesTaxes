package io.github.salestaxes.model.impl;

import io.github.salestaxes.model.Product;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Objects;

import static org.junit.Assert.*;

public class ConcreteProductTest {

    @Before
    public void setUp() {
    }

    @Test
    public void testGetProductNameToBeTrue() {
        // Setup
        final String expected = "name";
        final Product target = ConcreteProduct.builder()
                .name(expected)
                .imported(false)
                .unitPrice(new BigDecimal("10.00"))
                .build();
        // Run the test
        final String result = target.getName();
        // Verify the results
        Assert.assertEquals(expected, result);
    }

    @Test(expected = NullPointerException.class)
    public void testGetProductNameNullToBeTrue() {
        // Setup
        ConcreteProduct.builder()
                .imported(true)
                .unitPrice(new BigDecimal("10.00"))
                .build();
    }

    @Test
    public void testGetProductImported() {
        // Setup
        final Boolean expected = true;
        final Product target = ConcreteProduct.builder()
                .name("name")
                .imported(true)
                .unitPrice(new BigDecimal("10.00"))
                .build();
        // Run the test
        final Boolean result = target.getImported();
        // Verify the results
        Assert.assertEquals(expected, result);
    }

    @Test(expected = NullPointerException.class)
    public void testGetProductImportedNullToBeTrue() {
        // Setup
        ConcreteProduct.builder()
                .name("name")
                .unitPrice(new BigDecimal("10.00"))
                .build();
    }

    @Test
    public void testGetUnitPrice() {
        // Setup
        final BigDecimal expected = new BigDecimal("10.00");
        final Product target = ConcreteProduct.builder()
                .name("name")
                .imported(true)
                .unitPrice(expected)
                .build();
        // Run the test
        final BigDecimal result = target.getUnitPrice();
        // Verify the results
        Assert.assertEquals(expected, result);
    }

    @Test(expected = NullPointerException.class)
    public void testGetProductUnitPriceNullToBeTrue() {
        // Setup
        ConcreteProduct.builder()
                .name("name")
                .imported(true)
                .build();
    }

    @Test
    public void testToString() {
        // Setup
        final String expectedResult = "ConcreteProduct(name=name, imported=true, unitPrice=10.00)";
        final Product target = ConcreteProduct.builder()
                .name("name")
                .imported(true)
                .unitPrice(new BigDecimal("10.00"))
                .build();
        // Run the test
        final String result = target.toString();
        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testEqualsSameObject() {
        // Setup
        final Product target = ConcreteProduct.builder()
                .name("name")
                .imported(true)
                .unitPrice(new BigDecimal("10.00"))
                .build();
        // Run the test
        final boolean result = target.equals(target);
        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testEqualsDifferentType() {
        // Setup
        final Product target = ConcreteProduct.builder()
                .name("name")
                .imported(true)
                .unitPrice(new BigDecimal("10.00"))
                .build();
        // Run the test
        final boolean result = target.equals(new Object());
        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testEqualsToBeTrue() {
        // Setup
        final Product expected = ConcreteProduct.builder()
                .name("name")
                .imported(true)
                .unitPrice(new BigDecimal("10.00"))
                .build();
        final Product target = ConcreteProduct.builder()
                .name("name")
                .imported(true)
                .unitPrice(new BigDecimal("10.00"))
                .build();
        // Run the test
        final boolean result = Objects.equals(expected, target);
        // Verify the results
        assertTrue(result);
    }

    @After
    public void tearDown() throws Exception {
    }
}