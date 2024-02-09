package com.example.authstarterkotlin.core.utils.validation
import ValidationResult
import android.util.Patterns
import java.util.regex.Matcher
import java.util.regex.Pattern

fun validateFiled(email: String): ValidationResult {
        if (email.trim().isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Required filed"
            )
        }

        return ValidationResult(
            successful = true
        )
  }



