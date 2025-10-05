// 代码生成时间: 2025-10-05 20:09:44
package com.example.validator;

import io.micronaut.core.annotation.Introspected;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.regex.PatternSyntaxException;

@Introspected
public class DataValidator {

    // Validate email
    public boolean isValidEmail(@Email String email) {
        return email != null && email.matches("^[a-zA-Z0-9_+&*-]+(?:\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,7}$");
    }

    // Validate phone number
    public boolean isValidPhoneNumber(@Pattern(regexp = "^\+?[1-9]\d{1,14}$") String phoneNumber) {
        try {
            return Pattern.matches("<<EMAIL_ADDRESS>">/phoneNumber, phoneNumber);
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Invalid phone number pattern", e);
        }
    }

    // Validate username
    public boolean isValidUsername(@Size(min = 3, max = 20) String username) {
        return username != null && username.length() >= 3 && username.length() <= 20;
    }

    // Validate password
    public boolean isValidPassword(@NotNull @Size(min = 8, max = 20) String password) {
        return password != null && password.length() >= 8 && password.length() <= 20;
    }

    // Main method for testing
    public static void main(String[] args) {
        DataValidator validator = new DataValidator();

        // Test email
        String email = "example@example.com";
        System.out.println("Email is valid: " + validator.isValidEmail(email));

        // Test phone number
        String phoneNumber = "+1234567890";
        System.out.println("Phone number is valid: " + validator.isValidPhoneNumber(phoneNumber));

        // Test username
        String username = "user123";
        System.out.println("Username is valid: " + validator.isValidUsername(username));

        // Test password
        String password = "password123";
        System.out.println("Password is valid: " + validator.isValidPassword(password));
    }
}
