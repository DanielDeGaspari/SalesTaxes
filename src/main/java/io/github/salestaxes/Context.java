package io.github.salestaxes;

import io.github.salestaxes.model.*;
import io.github.salestaxes.model.impl.BasicTax;
import io.github.salestaxes.model.impl.ConcreteReceipt;
import io.github.salestaxes.model.impl.DecoratedPurchase;
import io.github.salestaxes.model.impl.ImportedTax;
import io.github.salestaxes.services.BasketReader;
import io.github.salestaxes.services.ResponseWriter;
import io.github.salestaxes.services.XMLReader;
import io.github.salestaxes.services.impl.ConcreteBasketReader;
import io.github.salestaxes.services.impl.ConcreteXMLReader;
import io.github.salestaxes.services.impl.ResponseWriterImpl;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Context {

    private final static Logger LOG = Logger.getLogger(Context.class.getName());

    final private BasketReader basketReader = new ConcreteBasketReader();
    final private XMLReader xmlReader = new ConcreteXMLReader();
    final private ResponseWriter responseWriter = new ResponseWriterImpl();
    private Map<String, String> categories;

    /**
     * Build context object that read a shopping basket and print out the receipt
     *
     * @param fileName File name of a shopping basket
     * @throws IOException                  IOException
     * @throws ParserConfigurationException ParserConfigurationException
     * @throws SAXException                 SAXException
     */
    public Context(final String fileName, Integer num) throws IOException, ParserConfigurationException, SAXException {
        LOG.log(Level.INFO, String.format("Starting creating context"));
        this.categories = this.xmlReader.parseCategories();
        LOG.log(Level.INFO, String.format("Parsed Categories: %s", categories));
        // Read file
        final File file = new File(fileName);
        final Stream<String> inputStream = Files.lines(file.toPath());
        // Parse shopping basket
        final Basket basket = this.basketReader.parse(inputStream);
        LOG.log(Level.INFO, String.format("Parsed Basket: %s", basket));
        // Create receipt
        final Receipt receipt = createReceipt(basket);
        LOG.log(Level.INFO, String.format("Created receipt: %s", receipt));
        // Get receipt in String format
        final String result = this.responseWriter.writeResponse(receipt);
        // Print result on stdOut;
        System.out.println(result);
        // Print result on a file
        printFile(num, result);
        LOG.log(Level.INFO, String.format("Context created."));
    }

    /**
     * Build receipt from given basket
     *
     * @param basket Basket
     * @return A {@link Receipt} Receipt of given basket
     */
    private Receipt createReceipt(final Basket basket) {
        LOG.log(Level.INFO, String.format("Starting receipt's creation."));
        final Receipt receipt = new ConcreteReceipt();
        // Iterate over purchases and add into receipt, comprehensive of tax
        basket.getPurchases().forEach(purchase -> {
            // calculate tax for current purchase
            LOG.log(Level.INFO, String.format("Processing purchase %s", purchase));
            final BigDecimal currentTax = calculateTax(purchase);
            LOG.log(Level.INFO, String.format("Tax calculated for purchase %s : %s", purchase, currentTax));
            // prepares taxed purchase
            final Purchase purchaseAfterTax = preparePurchase(purchase, currentTax);
            LOG.log(Level.INFO, String.format("Purchase after tax calculated: %s", purchaseAfterTax));
            // add purchase to receipt
            receipt.addPurchase(purchaseAfterTax);
            LOG.log(Level.INFO, String.format("Purchase %s added to receipt", purchase));
        });
        LOG.log(Level.INFO, "Receipt created");
        return receipt;
    }

    /**
     * Get decorated purchase with tax only if calculated tax is not equals 0;
     * given purchase otherwise
     *
     * @param purchase Purchase before tax
     * @param tax      tax
     * @return A {@link Purchase} Return decorated purchase with tax only if tax is not equals 0;
     * return given purchase otherwise
     */
    private Purchase preparePurchase(final Purchase purchase, final BigDecimal tax) {
        Purchase result = purchase;
        // Tax must be applied
        LOG.log(Level.INFO, String.format("Decorating purchase %s with tax %s", purchase, tax));
        if (tax.compareTo(BigDecimal.ZERO) != 0) {
            result = DecoratedPurchase.decorate(purchase, tax);
        }
        LOG.log(Level.INFO, String.format("Decorated purchase: %s", result));
        return result;
    }

    /**
     * Calculate total tax for given purchase
     *
     * @param purchase Purchase before tax
     * @return A {@link BigDecimal} Calculated tax
     */
    private BigDecimal calculateTax(final Purchase purchase) {
        LOG.log(Level.INFO, String.format("Starting calculating tax for purchase %s", purchase));
        BigDecimal tax = BigDecimal.ZERO;
        final Product product = purchase.getProduct();
        LOG.log(Level.INFO, String.format("Product: %s", product));
        // Product is imported
        if (product.getImported()) {
            final Tax importTax = new ImportedTax();
            tax = tax.add(importTax.calculateTax(purchase.getPurchasePrice()));
            LOG.log(Level.INFO, String.format("Product is imported: adding tax %s", tax));
        }
        // Product is not exempt
        if (BasicTax.isBasicTaxApplicable(categories, product)) {
            final Tax basicTax = new BasicTax();
            tax = tax.add(basicTax.calculateTax(purchase.getPurchasePrice()));
            LOG.log(Level.INFO, String.format("Basic tax can be applied: adding tax %s", tax));
        }
        LOG.log(Level.INFO, String.format("Calculated tax for purchase %s : %s", purchase, tax));
        return tax;
    }

    /**
     * Print output in a file
     *
     * @param num    number
     * @param output output
     * @throws IOException IOException
     */
    private void printFile(final Integer num, final String output) throws IOException {
        final String outputFileName = "samples/Purchase" + num + "Generated.txt";
        LOG.log(Level.INFO, String.format("Starting writing receipt %s in file: %s", output, outputFileName));
        File file = new File(outputFileName);
        // Create new file if not exist
        if (!file.exists()) {
            file.createNewFile();
            LOG.log(Level.INFO, String.format("File %s created", outputFileName));
        }
        try {
            // Write the content into the file
            FileWriter fw = new FileWriter(outputFileName);
            fw.write(output);
            fw.close();
        } catch (Exception e) {
            System.out.println(String.format("Error trying to write Purchase on file : %s)", e));
        }
        LOG.log(Level.INFO, String.format("Finished writing receipt in file: %s", outputFileName));
    }

}
