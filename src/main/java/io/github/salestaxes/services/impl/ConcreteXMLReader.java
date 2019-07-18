package io.github.salestaxes.services.impl;

import io.github.salestaxes.services.XMLReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.github.salestaxes.Utils.Constants.*;

public class ConcreteXMLReader implements XMLReader {

    private final static Logger LOG = Logger.getLogger(ConcreteXMLReader.class.getName());

    @Override
    public Map<String, String> parseCategories()
            throws IOException, SAXException, ParserConfigurationException {
        LOG.log(Level.INFO, "Starting parsing categories");
        // Read XML containing categories
        File xmlFile = new File(CATEGORIES_PATH);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);
        // Get all products as a NodeList
        NodeList nodeList = document.getElementsByTagName(PRODUCT_TAG);
        final Map<String, String> result = populateMap(nodeList);
        LOG.log(Level.INFO, String.format("Categories parsed: %s", result));
        return result;
    }

    /**
     * Populate map reading given node list
     *
     * @param nList products's list of nodes
     * @return A {@link Map<String>} Return a map with node list's values
     */
    private static Map<String, String> populateMap(final NodeList nList) {
        LOG.log(Level.INFO, "Starting populating categories map");
        Map<String, String> result = new HashMap<>();
        // Iterate over products
        for (int i = 0; i < nList.getLength(); i++) {
            final Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                // Get name and category of current product
                Element current = (Element) node;
                final String name = current.getElementsByTagName(NAME_TAG).item(0).getTextContent();
                final String category = current.getElementsByTagName(CATEGORY_TAG).item(0).getTextContent();
                LOG.log(Level.INFO, String.format("Parsed category: name = %s ; category = %s ", name, category));
                // Add product to result map
                result.put(name, category);
            }
        }
        LOG.log(Level.INFO, String.format("Categories map populated %s", result));
        return result;
    }

}
