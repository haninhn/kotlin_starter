import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.authstarterkotlin.core.compnents.CustomTextField
import com.example.authstarterkotlin.core.navigation.Screen
import com.example.authstarterkotlin.student.presentation.add_student_screen.AddEditEvent
import com.example.authstarterkotlin.student.presentation.add_student_screen.AddEditeViewModel
import com.example.authstarterkotlin.ui.theme.label
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.getViewModel

@Composable
fun AddEditStudent(
    navigation: NavController,
    id: String,

    ) {
    val viewModel = getViewModel<AddEditeViewModel>()
    val state = viewModel.state.value

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val systemUiController = rememberSystemUiController()
    val coroutineScope = rememberCoroutineScope()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color(0xFF3757FF),
            darkIcons = false
        )
        systemUiController.isNavigationBarVisible = false
    }

    LaunchedEffect(key1 = state.successAdd) { // Modify the key to state.successAdd
        if (state.successAdd) {
            navigation.navigate(Screen.AllStudentScreen.route)
            Toast.makeText(context, "Student added successfully", Toast.LENGTH_SHORT).show()
            // navigation.popBackStack()
            print("success")
        }
    }



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Add Student") },
                backgroundColor = Color(0xFF3757FF),
                contentColor = Color.White,
                elevation = 4.dp
            )
        },
        content = {
            Column(
                modifier = Modifier.background(color = Color(0xFFDCE9FF))
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp),

                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Enter student data below",
                    style = MaterialTheme.typography.h5.copy(color = Color(0xFF000000) ),
                )
                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp ),
                    text = "user Name",
                    style = label.copy(color = Color(0xFF000000)),
                )
                Spacer(modifier = Modifier.height(10.dp))
                CustomTextField(
                    text = state.userName,
                    onValueChange = { it ->
                        viewModel.onEvent(AddEditEvent.UserNameChanged(it))
                    },
                    hint = "Enter User Name",
                    keyboardType = KeyboardType.Email,
                    error = state.userNameError,

                )

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp ),
                    text = "Card",
                    style = label.copy(color = Color(0xFF000000)),
                )
                Spacer(modifier = Modifier.height(10.dp))
                CustomTextField(
                    text = state.card,
                    onValueChange = { it ->
                        viewModel.onEvent(AddEditEvent.CardChanged(it))
                    },
                    hint = "Enter Student Card ",
                    keyboardType = KeyboardType.Email,
                    error = state.cardError,
                    )

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp ),
                    text = "Division",
                    style = label.copy(color = Color(0xFF000000)),
                )
                Spacer(modifier = Modifier.height(10.dp))
                CustomTextField(
                    text = state.division,
                    onValueChange = {
                        viewModel.onEvent(AddEditEvent.DivisionChanged(it))
                    },
                    hint = "Enter Student division ",
                    keyboardType = KeyboardType.Email,
                    error = state.divisionError,
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 23.dp),
                ) {
                    CustomButtonView(
                        modifier = Modifier
                            .align(Alignment.BottomCenter),
                        text = "Confirm",
                        onClick = {
                            if(id != ""){
                                viewModel.onEvent(AddEditEvent.UpdateBtn)
                            }else
                               viewModel.onEvent(AddEditEvent.SubmitBtn)
                        }
                    )
                }        // Button to submit the form

            }
        }
    )
}



