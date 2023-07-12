import androidx.compose.ui.graphics.Color

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null,
)