package io.github.salestaxes.services;

import io.github.salestaxes.model.Receipt;
import lombok.NonNull;

public interface ResponseWriter {

    /**
     * Write receipt content in String format
     *
     * @param receipt receipt
     * @return a {@link String} Receipt content
     */
    public String writeResponse(final @NonNull Receipt receipt);

}
