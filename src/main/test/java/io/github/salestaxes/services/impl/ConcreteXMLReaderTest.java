package io.github.salestaxes.services.impl;

import io.github.salestaxes.services.XMLReader;
import io.github.salestaxes.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ConcreteXMLReaderTest {

    private XMLReader xmlReader;

    @Before
    public void setUp() {
        xmlReader = new ConcreteXMLReader();
    }

    @Test
    public void testParseToBeTrue() throws Exception {
        // Setup
        final Map<String, String> expectedResult = TestUtils.populateExpectedCategoryMap();
        // Run the test
        final Map<String, String> result = xmlReader.parseCategories();
        // Verify the results
        assertEquals(expectedResult, result);
    }

}
