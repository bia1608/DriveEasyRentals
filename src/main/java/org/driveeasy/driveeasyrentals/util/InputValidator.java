package org.driveeasy.driveeasyrentals.util;

import org.driveeasy.driveeasyrentals.exception.ValidationException;

import java.util.regex.Pattern;

public final class InputValidator {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^[0-9+\\-\\s]{7,20}$");

    private InputValidator() {
    }

    public static void validateEmail(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new ValidationException("Invalid email format: " + email);
        }
    }

    public static void validatePhone(String phone) {
        if (phone == null || !PHONE_PATTERN.matcher(phone).matches()) {
            throw new ValidationException("Invalid phone format: " + phone);
        }
    }
}
