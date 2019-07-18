package io.github.salestaxes.Utils;

import java.util.regex.Pattern;

public final class Constants {

    public static final String IMPORTED = "imported";
    public static final String QUANTITY = "quantity";
    public static final String NAME = "name";
    public static final String UNIT_PRICE = "unitPrice";

    // Text pattern
    private static final String OUTPUT_TEXT = "(?<quantity>\\d+)\\s+(?<name>.+)\\s+at\\s+(?<unitPrice>\\S+)";
    public static final Pattern OUTPUT_TEXT_PATTERN = Pattern.compile(Constants.OUTPUT_TEXT);

    public static final String CATEGORIES_PATH = "src//main//resources//categories.xml";

    // XML tag name
    public static final String PRODUCTS_ROOT_TAG = "products";
    public static final String PRODUCT_TAG = "product";
    public static final String NAME_TAG = "name";
    public static final String CATEGORY_TAG = "category";

    // Category
    public static final String BOOKS = "books";
    public static final String FOOD = "food";
    public static final String MEDICAL_PRODUCTS = "medical products";
    public static final String[] EXCLUDED_CATEGORIES = new String[]{BOOKS, FOOD, MEDICAL_PRODUCTS};

    // Tax
    public static final String BASIC_TAX = "0.1";
    public static final String IMPORTED_TAX = "0.05";

    /**
     The caller references the constants using <tt>Constants.NAME</tt>.
     Thus, the caller should be prevented from constructing objects of
     this class, by declaring this private constructor.
     */
    private Constants(){}
}
