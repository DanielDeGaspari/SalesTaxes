package io.github.salestaxes.model.impl;

import io.github.salestaxes.model.Product;
import lombok.*;

import java.math.BigDecimal;

@Builder
@EqualsAndHashCode
@Getter
@ToString
public class ConcreteProduct implements Product {

    @NonNull
    private String name;
    @NonNull
    private Boolean imported;
    @NonNull
    private BigDecimal unitPrice;

}
