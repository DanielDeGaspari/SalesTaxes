package io.github.salestaxes.model;

import java.math.BigDecimal;

public interface Product {

    /**
     * Get item name
     * @return A {@link String} item name
     */
    public String getName();

    /**
     * Get item imported status
     * @return A {@link Boolean} return true only if item
     * is imported; false otherwise
     */
    public Boolean getImported();

    /**
     * Get item unit price before taxes
     * @return A {@link BigDecimal} item unit price before taxes
     */
    public BigDecimal getUnitPrice();

    /**
     * Compare two item objects
     * @param o item to compare
     * @return A {@link boolean} true only if given items are equals;
     * return false otherwise
     */
    public boolean equals(Object o);

    /**
     * Get item's object hashcode
     * @return A {@link int} hashcode for item's object hashcode
     */
    public int hashCode();

    /**
     * Get item as a String
     * @return A {@link String} Get item as a String
     */
    public String toString();
}
