package com.hbs.hotell.util;

import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public static boolean validEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static int isNumber(TextField input) {

        if (input.getText().matches("^\\d*$"))
        {
            return Integer.parseInt(input.getText());
        }
        return 0;
    }

    public static boolean hasNumbers(String input) {

        return input.matches("^\\d*$");
    }
    // ei tööta
    public static boolean hasDate(LocalDate date) {
        Optional<LocalDate> optionalDate = Optional.ofNullable(date);
        if (optionalDate.isPresent()) {
            // LocalDate has no value
            return false;
        } else {
            // LocalDate has a value
            return true;
        }
    }
}
