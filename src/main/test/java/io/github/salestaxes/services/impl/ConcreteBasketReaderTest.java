package io.github.salestaxes.services.impl;

import io.github.salestaxes.model.Basket;
import io.github.salestaxes.model.Product;
import io.github.salestaxes.model.impl.ConcreteBasket;
import io.github.salestaxes.model.impl.ConcreteProduct;
import io.github.salestaxes.model.impl.ConcretePurchase;
import io.github.salestaxes.services.BasketReader;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConcreteBasketReaderTest {

    private BasketReader basketReader;

    @Before
    public void setUp() {
        basketReader = new ConcreteBasketReader();
    }

    @Test
    public void testParseWithOnly1EmptyLine() {
        // Setup
        final List<String> list = new ArrayList<>();
        list.add(StringUtils.EMPTY);
        final Basket expectedResult = new ConcreteBasket();
        // Run the test
        final Basket result = basketReader.parse(list.stream());
        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testParseWith1EmptyLineAndProducts() {
        // Setup
        final List<String> list = new ArrayList<>();
        list.add(StringUtils.EMPTY);
        list.add("1 book at 12.49");
        final Basket expectedResult = new ConcreteBasket();
        final Product expectedProduct = ConcreteProduct.builder()
                .name("book")
                .unitPrice(new BigDecimal("12.49"))
                .imported(false)
                .build();
        expectedResult.addPurchase(ConcretePurchase.builder()
                .product(expectedProduct)
                .quantity(1)
                .build());
        // Run the test
        final Basket result = basketReader.parse(list.stream());
        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testParseWith1ProductImported() {
        // Setup
        final List<String> list = new ArrayList<>();
        list.add("2 imported box of chocolates at 10.00");
        final Basket expectedResult = new ConcreteBasket();
        final Product expectedProduct = ConcreteProduct.builder()
                .name("box of chocolates")
                .unitPrice(new BigDecimal("10.00"))
                .imported(true)
                .build();
        expectedResult.addPurchase(ConcretePurchase.builder()
                .product(expectedProduct)
                .quantity(2)
                .build());
        // Run the test
        final Basket result = basketReader.parse(list.stream());
        // Verify the results
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testParseWith1ProductImportedNotAtBegin() {
        // Setup
        final List<String> list = new ArrayList<>();
        list.add("2 box of imported chocolates at 10.00");
        final Basket expectedResult = new ConcreteBasket();
        final Product expectedProduct = ConcreteProduct.builder()
                .name("box of chocolates")
                .unitPrice(new BigDecimal("10.00"))
                .imported(true)
                .build();
        expectedResult.addPurchase(ConcretePurchase.builder()
                .product(expectedProduct)
                .quantity(2)
                .build());
        // Run the test
        final Basket result = basketReader.parse(list.stream());
        // Verify the results
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testParseWith1ProductInWrongFormatNoQuantity() {
        // Setup
        final List<String> list = new ArrayList<>();
        list.add("book at 12.49");
        final Basket expectedResult = new ConcreteBasket();
        // Run the test
        final Basket result = basketReader.parse(list.stream());
        // Verify the results
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testParseWith2ProductsInWrongFormatNoQuantity() {
        // Setup
        final List<String> list = new ArrayList<>();
        list.add("book at 12.49");
        list.add("2 imported box of chocolates at 10.00");
        final Basket expectedResult = new ConcreteBasket();
        final Product expectedProduct = ConcreteProduct.builder()
                .name("box of chocolates")
                .unitPrice(new BigDecimal("10.00"))
                .imported(true)
                .build();
        expectedResult.addPurchase(ConcretePurchase.builder()
                .product(expectedProduct)
                .quantity(2)
                .build());
        // Run the test
        final Basket result = basketReader.parse(list.stream());
        // Verify the results
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testParseWith1ProductInWrongFormatMissingAt() {
        // Setup
        final List<String> list = new ArrayList<>();
        list.add("1 book 12.49");
        final Basket expectedResult = new ConcreteBasket();
        // Run the test
        final Basket result = basketReader.parse(list.stream());
        // Verify the results
        Assert.assertEquals(expectedResult, result);
    }
}
