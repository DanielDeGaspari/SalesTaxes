package io.github.salestaxes.model.impl;

import io.github.salestaxes.model.Basket;
import io.github.salestaxes.model.Purchase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@EqualsAndHashCode
@Getter
@ToString
public class ConcreteBasket implements Basket {

    private final static Logger LOG = Logger.getLogger(ConcreteBasket.class.getName());
    private List<Purchase> purchases;

    public ConcreteBasket() {
        this.purchases = new ArrayList<>();
    }

    @Override
    public void addPurchase(final @NonNull Purchase purchase) {
        LOG.log(Level.INFO, String.format("Adding purchase %s to shopping basket", purchase));
        this.purchases.add(purchase);
    }

}
