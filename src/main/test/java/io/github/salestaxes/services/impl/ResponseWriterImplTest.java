package io.github.salestaxes.services.impl;

import io.github.salestaxes.model.Product;
import io.github.salestaxes.model.Purchase;
import io.github.salestaxes.model.impl.ConcreteProduct;
import io.github.salestaxes.model.impl.ConcretePurchase;
import io.github.salestaxes.model.impl.ConcreteReceipt;
import io.github.salestaxes.services.ResponseWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ResponseWriterImplTest {

    private ResponseWriter responseWriter;
    private ConcreteReceipt concreteReceipt;

    @Before
    public void setUp() {
        final Product product1 = ConcreteProduct.builder()
                .imported(true)
                .name("name")
                .unitPrice(BigDecimal.TEN)
                .build();
        final Product product2 = ConcreteProduct.builder()
                .imported(false)
                .name("name")
                .unitPrice(new BigDecimal("5"))
                .build();
        final Purchase purchase1 = ConcretePurchase.builder()
                .product(product1)
                .quantity(1)
                .build();
        final Purchase purchase2 = ConcretePurchase.builder()
                .product(product2)
                .quantity(3)
                .build();
        concreteReceipt = new ConcreteReceipt();
        concreteReceipt.addPurchase(purchase1);
        concreteReceipt.addPurchase(purchase2);
        responseWriter = new ResponseWriterImpl();
    }

    @Test
    public void testWriteResponseEmptyPurcheases() {
        // Setup
        final String expectedResult =
                "Sales Taxes: 0\n" +
                "Total: 0";
        concreteReceipt = new ConcreteReceipt();
        // Run the test
        final String result = responseWriter.writeResponse(concreteReceipt);
        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testWriteResponse2Purcheases() {
        // Setup
        final String expectedResult =
                "1 imported name : 10\n"
                + "3 name : 15\n"
                + "Sales Taxes: 0\n"
                + "Total: 25";
        // Run the test
        final String result = responseWriter.writeResponse(concreteReceipt);
        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test(expected = NullPointerException.class)
    public void testWriteResponseWithNullRecipt() {
        // Setup
        final ConcreteReceipt concreteReceipt = null;
        final String expectedResult = "result";
        // Run the test
        final String result = responseWriter.writeResponse(concreteReceipt);
        // Verify the results
    }

    @After
    public void tearDown() throws Exception {

    }
}
