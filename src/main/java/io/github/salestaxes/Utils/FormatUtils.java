package io.github.salestaxes.Utils;

import io.github.salestaxes.Context;
import org.apache.commons.lang3.StringUtils;

import java.text.Normalizer;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class FormatUtils {

    private final static Logger LOG = Logger.getLogger(FormatUtils.class.getName());

    private static final Pattern NONLATIN = Pattern.compile("[^\\w_-]");
    private static final Pattern SEPARATORS = Pattern.compile("[\\s\\p{Punct}&&[^-]]");

    /**
     * Create slug version of the provided input
     * A slug is a string representation valid for URL
     * i.e. removes space and non-standard characters
     * "fóò bâr" -> "foo-bar"
     *
     * @param input input
     * @return A String representing the slug version of the input
     */
    public static String slugify(final String input) {
        String result;
        String noseparators = SEPARATORS.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(noseparators, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        result = slug.toLowerCase(Locale.ENGLISH).replaceAll("-{2,}", "-").replaceAll("^-|-$", "");
        LOG.log(Level.INFO, String.format("Slugify input: %s converted in: %s", input, result));
        return result;
    }

}
