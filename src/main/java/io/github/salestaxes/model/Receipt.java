package io.github.salestaxes.model;

import io.github.salestaxes.model.impl.ConcretePurchase;

import java.math.BigDecimal;
import java.util.List;

public interface Receipt {

    /**
     * Get receipt's total
     *
     * @return A {@link BigDecimal} receipt's total
     */
    public BigDecimal getTotal();

    /**
     * Get receipt's total sales taxes
     *
     * @return A {@link BigDecimal}total sales taxes
     */
    public BigDecimal getTotalTax();

    /**
     * Get receipt's purchases's list
     *
     * @return A {@link List<ConcretePurchase>} receipt's purchases's list
     */
    public List<Purchase> getPurchases();

    /**
     * Add a purchase to purchases's list
     *
     * @param purchase purchase list
     */
    public void addPurchase(final Purchase purchase);

    /**
     * Compare two receipt
     *
     * @param o receipt to compare
     * @return A {@link boolean} true only if given receipts are equals;
     * return false otherwise
     */
    public boolean equals(Object o);

    /**
     * Get ConcreteReceipt's object hashcode
     *
     * @return A {@link int} hashcode for ConcreteReceipt's object hashcode
     */
    public int hashCode();

    /**
     * Get ConcreteReceipt as a String
     *
     * @return A {@link String} Get ConcreteReceipt as a String
     */
    public String toString();
}
