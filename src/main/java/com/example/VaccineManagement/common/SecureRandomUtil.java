package com.example.VaccineManagement.common;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.EncoderConstants;
import org.owasp.esapi.StringUtilities;

public class SecureRandomUtil {

    private static final int DEF_COUNT = 20;

    private SecureRandomUtil() {
    }

    public static String randomAlphanumeric() {
        return randomAlphanumeric(DEF_COUNT);
    }

    /**
     * <p>Creates a random string whose length is the number of characters
     * specified.</p>
     *
     * <p>Characters will be chosen from the set of Latin alphabetic
     * characters (a-z, A-Z) and the digits 0-9.</p>
     *
     * @param count the length of random string to create
     * @return the random string
     */
    public static String randomAlphanumeric(int count) {
        return ESAPI.randomizer().getRandomString(count, EncoderConstants.CHAR_ALPHANUMERICS);
    }

    public static String randomAlphanumericUppers(int count) {
        char[] alphanumeric = StringUtilities.union(EncoderConstants.CHAR_UPPERS, EncoderConstants.CHAR_DIGITS);
        return ESAPI.randomizer().getRandomString(count, alphanumeric);
    }

    public static String randomAlphabetic(int count) {
        return ESAPI.randomizer().getRandomString(count, EncoderConstants.CHAR_UPPERS);
    }

    public static String randomNumeric(final int count) {
        return ESAPI.randomizer().getRandomString(count, EncoderConstants.CHAR_DIGITS);
    }
}
