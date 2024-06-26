package com.nashss.se.stockwatchlist.utils;

import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public final class watchlistServiceUtils {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern INVALID_CHARACTER_PATTERN = Pattern.compile("[\"'\\\\]");
    private static final Pattern EMAIL_MUST_CONTAIN_PATTERN = Pattern.compile(EMAIL_REGEX);
    private watchlistServiceUtils() {
    }

    public static boolean isValidString(String stringToValidate) {
        if (StringUtils.isBlank(stringToValidate)) {
            return false;
        } else {
            return !INVALID_CHARACTER_PATTERN.matcher(stringToValidate).find();
        }
    }

    public static boolean isValidEmail(String emailToValidate) {
        if (StringUtils.isBlank(emailToValidate)) {
            return false;
        }
        else {
            return !INVALID_CHARACTER_PATTERN.matcher(emailToValidate).find() && EMAIL_MUST_CONTAIN_PATTERN.matcher(emailToValidate).find();
        }
    }

}
