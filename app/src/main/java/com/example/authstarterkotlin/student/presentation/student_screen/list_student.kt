package com.example.authstarterkotlin.student.presentation.student_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.authstarterkotlin.core.navigation.Screen
import com.example.authstarterkotlin.student.presentation.add_student_screen.AddEditEvent
import com.example.authstarterkotlin.student.presentation.add_student_screen.AddEditeViewModel
import com.example.authstarterkotlin.ui.theme.label
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.getViewModel

@Composable
fun ListStudent(navigation: NavController) {
    val viewModel = getViewModel<StudentsViewModel>()
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

    LaunchedEffect(key1 = state.success) {
        if (state.success) {
            Toast.makeText(context, "Student deleted successfully", Toast.LENGTH_SHORT).show()
            // navigation.popBackStack()
            print("success")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Students") },
                backgroundColor = Color(0xFF3757FF),
                contentColor = Color.White,
                elevation = 4.dp,
                actions = {
                    IconButton(
                        onClick = {
                            navigation.navigate(Screen.AddEditStudent.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            tint = Color.White
                        )
                    }
                }
            )
        },
    content = {
            Column(
                modifier = Modifier
                    .background(color = Color(0xFFDCE9FF))
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp),

                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator()
                } else {
                    LazyColumn {
                        items(state.students) { student ->
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 14.dp, vertical = 10.dp)
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .border(
                                        width = 2.dp,
                                        color = Color(0xFFF3F4F6),
                                        shape = RoundedCornerShape(size = 14.dp)
                                    )
                                    .background(
                                        color = Color(0xFFFFFFFF),
                                        shape = RoundedCornerShape(size = 14.dp)
                                    )
                                    .clickable {
                                        navigation.navigate("${Screen.StudentScreen.route}/${student.userName}/${student.card}/${student.division}")
                                    },


                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,

                                    modifier = Modifier.padding(horizontal = 14.dp,
                                        vertical = 16.dp).weight(1f)

                                ) {
                                    Text(
                                        text = student.userName,
                                        modifier = Modifier.padding(horizontal = 16.dp),
                                        style =  label.copy(color = Color(0xFF000000), fontSize = 18.sp),

                                        )

                                    Text(
                                        text = student.card,
                                        modifier = Modifier
                                            .padding(horizontal = 16.dp),
                                        style =  label.copy(color = Color(0xffA7A9AF), fontSize = 16.sp),


                                        )
                                }

                                // Icon for delete action
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete",
                                        tint = Color.Red,
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .size(24.dp)
                                            .clickable {
                                                viewModel.onEvent(StudentEvent.DeleteBtn(student.userName))
                                            }
                                    )

                                    // Icon for update action
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "Update",
                                        tint = Color.Green, // Adjust color if needed
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .size(24.dp)
                                            .clickable {
                                                navigation.navigate("${Screen.AddEditStudent.route}/${student.userName}/${student.card}/${student.division}")
                                            }
                                    )
                                }

                            }
                        }
                    }
                }

            }
     })
}