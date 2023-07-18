import androidx.lifecycle.ViewModel
import com.example.authstarterkotlin.feature_auth.domain.use_case.RegisterUseCase

class  RegisterViewModel(
    registerUseCase: RegisterUseCase
): ViewModel() {
   private  val state = mutableListOf(RegisterState)
    


}