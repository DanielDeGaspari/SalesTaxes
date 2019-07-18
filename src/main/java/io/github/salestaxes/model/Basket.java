package io.github.salestaxes.model;

import java.util.List;

public interface Basket {

    /**
     * Get basket's purchases's list
     *
     * @return A {@link List < ConcretePurchase >} basket's purchases's list
     */
    public List<Purchase> getPurchases();

    /**
     * Add a basket to basket's list
     *
     * @param purchase basket list
     */
    public void addPurchase(final Purchase purchase);
}
