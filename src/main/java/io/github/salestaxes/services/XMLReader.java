package io.github.salestaxes.services;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

public interface XMLReader {

    /**
     * Parse XML containing categories
     *
     * @return A {@link Map<>} Return a map with product's categories
     * @throws IOException                  IO Exception
     * @throws SAXException                 SAXException
     * @throws ParserConfigurationException ParserConfigurationException
     */
    public Map<String, String> parseCategories()
            throws IOException, SAXException, ParserConfigurationException;
}
