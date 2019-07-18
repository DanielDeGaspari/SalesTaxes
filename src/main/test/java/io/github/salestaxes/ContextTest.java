package io.github.salestaxes;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ContextTest {

    @Before
    public void setUp() {
    }

    @Test
    public void testSampleBasket1() throws ParserConfigurationException, SAXException, IOException {
        // Setup
        // Delete old files
        final String fileName = "samples/basket1.txt";
        final String expectedFileOutput = "samples/purchase1Expected.txt";
        final String fileNameOutput = "samples/Purchase1Generated.txt";
        // Run the test
        Context context = new Context(fileName, 1);
        final Boolean isContentEquals = FileUtils.contentEquals(new File(expectedFileOutput), new File(fileNameOutput));
        // Verify the results
        Assert.assertTrue(isContentEquals);
    }

    @Test
    public void testSampleBasket2() throws ParserConfigurationException, SAXException, IOException {
        // Setup
        // Delete old files
        final String fileName = "samples/basket2.txt";
        final String expectedFileOutput = "samples/purchase2Expected.txt";
        final String fileNameOutput = "samples/Purchase2Generated.txt";
        // Run the test
        Context context = new Context(fileName, 2);
        final Boolean isContentEquals = FileUtils.contentEquals(new File(expectedFileOutput), new File(fileNameOutput));
        // Verify the results
        Assert.assertTrue(isContentEquals);
    }

    @Test
    public void testSampleBasket3() throws ParserConfigurationException, SAXException, IOException {
        // Setup
        // Delete old files
        final String fileName = "samples/basket3.txt";
        final String expectedFileOutput = "samples/purchase3Expected.txt";
        final String fileNameOutput = "samples/Purchase3Generated.txt";
        // Run the test
        Context context = new Context(fileName, 3);
        final Boolean isContentEquals = FileUtils.contentEquals(new File(expectedFileOutput), new File(fileNameOutput));
        // Verify the results
        Assert.assertTrue(isContentEquals);
    }

    @After
    public void tearDown() throws Exception {

    }
}
