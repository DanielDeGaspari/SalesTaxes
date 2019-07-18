package io.github.salestaxes.model.impl;

import io.github.salestaxes.model.Basket;
import io.github.salestaxes.model.Product;
import io.github.salestaxes.model.Purchase;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ConcreteBasketTest {

    @Before
    public void setUp() {
    }

    @Test
    public void getPurchasesWithEmptyPurchases() {
        // Setup
        final List<Purchase> expected = new ArrayList<>();
        final Basket target = new ConcreteBasket();
        // Run the test
        final List<Purchase> purchasesList = target.getPurchases();
        // Verify the results
        assertEquals(expected, purchasesList);
    }

    @Test
    public void getTotalWith2Purchases() {
        // Setup
        final List<Purchase> expected = new ArrayList<>();
        final Product product1 = ConcreteProduct.builder()
                .imported(true)
                .name("name")
                .unitPrice(BigDecimal.TEN)
                .build();
        final Product product2 = ConcreteProduct.builder()
                .imported(true)
                .name("name")
                .unitPrice(new BigDecimal("5"))
                .build();
        final Purchase purchase1 = ConcretePurchase.builder()
                .product(product1)
                .quantity(1)
                .build();
        final Purchase purchase2 = ConcretePurchase.builder()
                .product(product2)
                .quantity(2)
                .build();
        final Basket target = new ConcreteBasket();
        target.addPurchase(purchase1);
        expected.add(purchase1);
        target.addPurchase(purchase2);
        expected.add(purchase2);
        // Run the test
        final List<Purchase> result = target.getPurchases();
        // Verify the results
        assertEquals(expected, result);
    }

    @Test
    public void getPurchases() {
        // Setup
        final List<Purchase> expected = new ArrayList<>();
        final Product product1 = ConcreteProduct.builder()
                .imported(true)
                .name("name")
                .unitPrice(BigDecimal.TEN)
                .build();
        final Product product2 = ConcreteProduct.builder()
                .imported(true)
                .name("name")
                .unitPrice(new BigDecimal("5"))
                .build();
        final Purchase purchase1 = ConcretePurchase.builder()
                .product(product1)
                .quantity(1)
                .build();
        final Purchase purchase2 = ConcretePurchase.builder()
                .product(product2)
                .quantity(2)
                .build();
        final Basket target = new ConcreteBasket();
        target.addPurchase(purchase1);
        expected.add(purchase1);
        target.addPurchase(purchase2);
        expected.add(purchase2);
        // Run the test
        final List<Purchase> result = target.getPurchases();
        // Verify the results
        assertEquals(expected, result);
    }

    @Test
    public void testToString() {
        // Setup
        final String expected = "ConcreteBasket(purchases=[ConcretePurchase(quantity=1, product=ConcreteProduct(name=name, imported=true, unitPrice=10)), ConcretePurchase(quantity=2, product=ConcreteProduct(name=name, imported=true, unitPrice=5))])";
        final Product product1 = ConcreteProduct.builder()
                .imported(true)
                .name("name")
                .unitPrice(BigDecimal.TEN)
                .build();
        final Product product2 = ConcreteProduct.builder()
                .imported(true)
                .name("name")
                .unitPrice(new BigDecimal("5"))
                .build();
        final Purchase purchase1 = ConcretePurchase.builder()
                .product(product1)
                .quantity(1)
                .build();
        final Purchase purchase2 = ConcretePurchase.builder()
                .product(product2)
                .quantity(2)
                .build();
        final Basket target = new ConcreteBasket();
        target.addPurchase(purchase1);
        target.addPurchase(purchase2);
        // Run the test
        final String result = target.toString();
        // Verify the results
        assertEquals(expected, result);
    }

    @Test
    public void testEqualsSameObject() {
        // Setup
        final Product product1 = ConcreteProduct.builder()
                .imported(true)
                .name("name")
                .unitPrice(BigDecimal.TEN)
                .build();
        final Product product2 = ConcreteProduct.builder()
                .imported(true)
                .name("name")
                .unitPrice(new BigDecimal("5"))
                .build();
        final Purchase purchase1 = ConcretePurchase.builder()
                .product(product1)
                .quantity(1)
                .build();
        final Purchase purchase2 = ConcretePurchase.builder()
                .product(product2)
                .quantity(2)
                .build();
        final Basket target = new ConcreteBasket();
        target.addPurchase(purchase1);
        target.addPurchase(purchase2);
        // Run the test
        final boolean result = target.equals(target);
        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testEqualsDifferentType() {
        // Setup
        final Product product1 = ConcreteProduct.builder()
                .imported(true)
                .name("name")
                .unitPrice(BigDecimal.TEN)
                .build();
        final Product product2 = ConcreteProduct.builder()
                .imported(true)
                .name("name")
                .unitPrice(new BigDecimal("5"))
                .build();
        final Purchase purchase1 = ConcretePurchase.builder()
                .product(product1)
                .quantity(1)
                .build();
        final Purchase purchase2 = ConcretePurchase.builder()
                .product(product2)
                .quantity(2)
                .build();
        final Basket target = new ConcreteBasket();
        target.addPurchase(purchase1);
        target.addPurchase(purchase2);
        // Run the test
        final boolean result = target.equals(new Object());
        // Verify the results
        assertFalse(result);
    }
}
