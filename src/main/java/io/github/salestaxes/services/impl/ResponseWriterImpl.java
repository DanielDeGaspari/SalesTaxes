package io.github.salestaxes.services.impl;

import io.github.salestaxes.model.Receipt;
import io.github.salestaxes.services.ResponseWriter;
import lombok.NonNull;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ResponseWriterImpl implements ResponseWriter {

    private final static Logger LOG = Logger.getLogger(ResponseWriterImpl.class.getName());

    @Override
    public String writeResponse(@NonNull final Receipt receipt) {
        LOG.log(Level.INFO, String.format("Starting writing response for receipt: %s", receipt));
        StringBuilder result = new StringBuilder();
        // Iterate over purchase and add them to result string
        receipt.getPurchases().forEach((purchase -> {
            LOG.log(Level.INFO, String.format("Writing purchase: %s", purchase));
            if (purchase.getProduct().getImported()) {
                result.append(purchase.getQuantity())
                        .append(" ")
                        .append("imported")
                        .append(" ")
                        .append(purchase.getProduct().getName())
                        .append(" : ")
                        .append(purchase.getPurchasePrice())
                        .append("\n");
            } else {
                result.append(purchase.getQuantity())
                        .append(" ")
                        .append(purchase.getProduct().getName())
                        .append(" : ")
                        .append(purchase.getPurchasePrice())
                        .append("\n");
            }
        }));
        LOG.log(Level.INFO, "Writing sales Taxes and total");
        // Add Sales Taxes and Total
        result.append("Sales Taxes: ")
                .append(receipt.getTotalTax())
                .append("\nTotal: ")
                .append(receipt.getTotal());
        LOG.log(Level.INFO, String.format("Response for receipt: %s", result.toString()));
        return result.toString();
    }

}
