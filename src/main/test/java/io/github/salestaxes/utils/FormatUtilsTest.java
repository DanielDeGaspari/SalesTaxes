package io.github.salestaxes.utils;

import io.github.salestaxes.Utils.FormatUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FormatUtilsTest {

    @Test
    public void testSlugifyToBeTrueSpecialChar() {
        // Setup
        final String input = "fóò bâr";
        final String expectedResult = "foo-bar";
        // Run the test
        final String result = FormatUtils.slugify(input);
        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSlugifyToBeTrueWhiteSpace() {
        // Setup
        final String input = "   book   ";
        final String expectedResult = "book";
        // Run the test
        final String result = FormatUtils.slugify(input);
        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSlugifyToBeTrueUpperCase() {
        // Setup
        final String input = "BOOK";
        final String expectedResult = "book";
        // Run the test
        final String result = FormatUtils.slugify(input);
        // Verify the results
        assertEquals(expectedResult, result);
    }
}
