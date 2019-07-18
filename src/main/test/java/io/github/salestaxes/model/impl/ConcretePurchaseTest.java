package io.github.salestaxes.model.impl;

import io.github.salestaxes.model.Product;
import io.github.salestaxes.model.Purchase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Objects;

import static org.junit.Assert.*;

public class ConcretePurchaseTest {

    @Mock
    private ConcreteProduct mockConcreteProduct;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetProductQuantity() {
        // Setup
        final Integer expected = 5;
        final Purchase target = ConcretePurchase.builder()
                .quantity(expected)
                .product(mockConcreteProduct)
                .build();
        // Run the test
        final Integer result = target.getQuantity();
        // Verify the results
        Assert.assertEquals(expected, result);
    }

    @Test(expected = NullPointerException.class)
    public void testGetPurchaseQuantityNullToBeTrue() {
        // Setup
        ConcretePurchase.builder()
                .product(mockConcreteProduct)
                .build();
    }

    @Test
    public void testGetPurchaseProduct() {
        // Setup
        final Purchase target = ConcretePurchase.builder()
                .quantity(5)
                .product(mockConcreteProduct)
                .build();
        // Run the test
        final Product result = target.getProduct();
        // Verify the results
        Assert.assertEquals(mockConcreteProduct, result);
    }

    @Test(expected = NullPointerException.class)
    public void testGetPurchaseProductNullToBeTrue() {
        // Setup
        ConcretePurchase.builder()
                .quantity(5)
                .build();
    }

    @Test
    public void testToString() {
        // Setup
        final String expectedResult = "ConcretePurchase(quantity=5, product=ConcreteProduct(name=name, imported=true, unitPrice=10.00))";
        final Product product = ConcreteProduct.builder()
                .name("name")
                .unitPrice(new BigDecimal("10.00"))
                .imported(true)
                .build();
        final ConcretePurchase target = ConcretePurchase.builder()
                .quantity(5)
                .product(product)
                .build();
        // Run the test
        final String result = target.toString();
        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testEqualsSameObject() {
        // Setup
        final Product product = ConcreteProduct.builder()
                .name("name")
                .unitPrice(new BigDecimal("10.00"))
                .imported(true)
                .build();
        final ConcretePurchase target = ConcretePurchase.builder()
                .quantity(5)
                .product(product)
                .build();
        // Run the test
        final boolean result = Objects.equals(target, target);
        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testEqualsDifferentType() {
        // Setup
        final Product product = ConcreteProduct.builder()
                .name("name")
                .unitPrice(new BigDecimal("10.00"))
                .imported(true)
                .build();
        final ConcretePurchase target = ConcretePurchase.builder()
                .quantity(5)
                .product(product)
                .build();
        // Run the test
        final boolean result = Objects.equals(target, 5);
        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testEqualsToBeTrue() {
        // Setup
        final Product product = ConcreteProduct.builder()
                .name("name")
                .unitPrice(new BigDecimal("10.00"))
                .imported(true)
                .build();
        final ConcretePurchase target = ConcretePurchase.builder()
                .quantity(5)
                .product(product)
                .build();
        final ConcretePurchase expected = ConcretePurchase.builder()
                .quantity(5)
                .product(product)
                .build();
        // Run the test
        final boolean result = Objects.equals(target, expected);
        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testGetPurchasePrice() {
        // Setup
        final BigDecimal expectedResult = new BigDecimal("5.00");
        final Product product = ConcreteProduct.builder()
                .name("name")
                .imported(false)
                .unitPrice(new BigDecimal("2.50"))
                .build();
        final Purchase target = ConcretePurchase.builder()
                .product(product)
                .quantity(2)
                .build();
        // Run the test
        final BigDecimal result = target.getPurchasePrice();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @After
    public void tearDown() throws Exception {
    }
}
