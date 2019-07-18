package io.github.salestaxes.utils;

import java.util.HashMap;
import java.util.Map;

public class TestUtils {

    /**
     * Populate expected result map
     *
     * @return A {@link Map <String,String>} represents product's categories
     */
    public static Map<String, String> populateExpectedCategoryMap() {
        final Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("book", "books");
        expectedResult.put("music-cd", "standard");
        expectedResult.put("chocolate-bar", "food");
        expectedResult.put("box-of-chocolates", "food");
        expectedResult.put("bottle-of-perfume", "standard");
        expectedResult.put("packet-of-headache-pills", "medical products");
        return expectedResult;
    }
}
