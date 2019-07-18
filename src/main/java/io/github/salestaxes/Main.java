package io.github.salestaxes;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private final static Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Wrong number of arguments");
            System.out.println("Usage: java -jar sales-taxes-calculator filename(s)");
            System.out.println("Example: java -jar sales-taxes-calculator basket1.txt");
        }

        int cont = 0;
        for (final String fileName : args) {
            cont++;
            LOG.log(Level.INFO, String.format("Starting processing basket: %s", fileName));
            try {
                System.out.println("Output " + cont);
                new Context(fileName, cont);
                System.out.println(StringUtils.EMPTY);
            } catch (IOException e) {
                LOG.log(Level.SEVERE, String.format("Unable to process basket %s", fileName));
                e.printStackTrace();
            } catch (SAXException e) {
                LOG.log(Level.SEVERE, String.format("SAXException occurs while processing file %s", fileName));
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                LOG.log(Level.SEVERE, String.format("ParserConfigurationException occurs while processing file %s", fileName));
                e.printStackTrace();
            }
            LOG.log(Level.INFO, String.format("Finished processing basket: %s", fileName));
        }
    }

}