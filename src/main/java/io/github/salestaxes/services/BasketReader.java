package io.github.salestaxes.services;

import io.github.salestaxes.model.Basket;

import java.util.stream.Stream;

public interface BasketReader {

    /**
     * Parse given stream and create basket
     *
     * @param stringStream basket's string stream
     * @return A {@link Basket} Basket created from given stream
     */
    public Basket parse(final Stream<String> stringStream);
}

