package com.example.authstarterkotlin.core.utils.validation
import ValidationResult
import android.util.Patterns
import java.util.regex.Matcher
import java.util.regex.Pattern

fun validateEmail(email: String): ValidationResult {
        if (email.trim().isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please enter your email"
            )
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "That's not a valid email"
            )
        }

        return ValidationResult(
            successful = true
        )
  }

fun validatePassword(password: String): ValidationResult {
    if (password.isBlank()) {
        return ValidationResult(
            successful = false,
            errorMessage = "Please enter your password"
        )
    }

    if (password.length < 8) {
        return ValidationResult(
            successful = false,
            errorMessage = "Password must be at least 8 characters length"
        )
    }

    val special: Pattern = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]")
    val hasSpecial: Matcher = special.matcher(password)

    val containsLettersAndDigitsAndSpecial =
        password.any { it.isDigit() } && password.any { it.isLetter() } &&
                password.any { it.isUpperCase() } && password.any { it.isLowerCase() } && hasSpecial.find()

    if (!containsLettersAndDigitsAndSpecial) {
        return ValidationResult(
            successful = false,
            errorMessage = "Password must contain at least one uppercase letter, one lowercase letter, one number and one special character"
        )
    }

    return ValidationResult(
        successful = true
    )
}

fun validateRepeatedPassword(password: String, repeatedPassword: String): ValidationResult {
    if (password != repeatedPassword) {
        return ValidationResult(
            successful = false,
            errorMessage = "The passwords don't match"
        )
    }

    return ValidationResult(
        successful = true
    )
}