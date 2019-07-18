package io.github.salestaxes.model;

import io.github.salestaxes.model.impl.ConcreteProduct;

import java.math.BigDecimal;

public interface Purchase {

    /**
     * Get purchase quantity of products
     * @return A {@link Integer} quantity of products
     */
    public Integer getQuantity();

    /**
     * Get ConcretePurchase product
     * @return A {@link ConcreteProduct} purchase's product
     */
    public Product getProduct();

    /**
     * Calculate purchase total price
     * @return A {@link BigDecimal}
     */
    public BigDecimal getPurchasePrice();

    /**
     * Compare two purchase objects
     * @param o purchase to compare
     * @return A {@link boolean} true only if given purchases are equals;
     * return false otherwise
     */
    public boolean equals(Object o);

    /**
     * Get ConcretePurchase's object hashcode
     * @return A {@link int} hashcode for ConcretePurchase's object hashcode
     */
    public int hashCode();

    /**
     * Get ConcretePurchase as a String
     * @return A {@link String} Get ConcretePurchase as a String
     */
    public String toString();

}
