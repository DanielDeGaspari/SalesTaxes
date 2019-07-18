package io.github.salestaxes.model.impl;

import io.github.salestaxes.model.Product;
import io.github.salestaxes.model.Purchase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class DecoratedPurchaseTest {

    private Product mockProduct;
    private DecoratedPurchase decoratedPurchase;

    @Before
    public void setUp() {
        initMocks(this);
        mockProduct = ConcreteProduct.builder()
                .name("name")
                .imported(true)
                .unitPrice(new BigDecimal("10.00"))
                .build();
    }

    @Test
    public void testGetProductQuantityWithBuilder() {
        // Setup
        final Integer expected = 5;
        final Purchase target = DecoratedPurchase.builder()
                .quantity(expected)
                .product(mockProduct)
                .build();
        // Run the test
        final Integer result = target.getQuantity();
        // Verify the results
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testGetProductQuantityWithCustomConstructor() {
        // Setup
        final Integer expected = 5;
        final Purchase target = new DecoratedPurchase(expected, mockProduct, new BigDecimal("1.50"));
        // Run the test
        final Integer result = target.getQuantity();
        // Verify the results
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testGetPurchaseProduct() {
        // Setup
        final Purchase target = DecoratedPurchase.builder()
                .quantity(5)
                .product(mockProduct)
                .build();
        // Run the test
        final Product result = target.getProduct();
        // Verify the results
        Assert.assertEquals(mockProduct, result);
    }

    @Test
    public void testGetPurchaseProductWithCustomConstructor() {
        // Setup
        final Purchase target = new DecoratedPurchase(5, mockProduct, new BigDecimal("1.50"));
        // Run the test
        final Product result = target.getProduct();
        // Verify the results
        Assert.assertEquals(mockProduct, result);
    }

    @Test
    public void testGetPurchasePriceWithCustomConstructor() {
        final BigDecimal expected = new BigDecimal("11.50");
        // Setup
        final Purchase target = new DecoratedPurchase(1, mockProduct, new BigDecimal("1.50"));
        // Run the test
        final BigDecimal finalPrice = target.getPurchasePrice();
        // Verify the results
        Assert.assertEquals(expected, finalPrice);
    }

    @Test
    public void testDecorateToBeTrue() {
        // Setup
        final Purchase purchase = DecoratedPurchase.builder()
                .quantity(1)
                .product(mockProduct)
                .build();
        final BigDecimal tax = new BigDecimal("1.50");
        final Purchase expectedResult = new DecoratedPurchase(1, mockProduct, tax);
        // Run the test
        final Purchase result = DecoratedPurchase.decorate(purchase, tax);
        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDecorateToBeFalseDifferentTax() {
        // Setup
        final Purchase purchase = DecoratedPurchase.builder()
                .quantity(1)
                .product(mockProduct)
                .build();
        final BigDecimal tax = new BigDecimal("1.50");
        final Purchase expectedResult = new DecoratedPurchase(1, mockProduct, tax);
        // Run the test
        final Purchase result = DecoratedPurchase.decorate(purchase, new BigDecimal("0.00"));
        // Verify the results
        assertNotEquals(expectedResult, result);
    }

    @Test
    public void testToString() {
        // Setup
        final String expectedResult = "DecoratedPurchase(super=ConcretePurchase(quantity=1, product=ConcreteProduct(name=name, imported=true, unitPrice=10.00)), tax=1.50)";
        final Purchase purchase = DecoratedPurchase.builder()
                .quantity(1)
                .product(mockProduct)
                .build();
        final BigDecimal tax = new BigDecimal("1.50");
        final Purchase decoratedPurchase = new DecoratedPurchase(1, mockProduct, tax);
        // Run the test
        final String result = decoratedPurchase.toString();
        // Verify the results
        assertEquals(expectedResult, result);
    }

    @After
    public void tearDown() throws Exception {
    }
}
