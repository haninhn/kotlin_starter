package com.example.authstarterkotlin

import com.example.authstarterkotlin.tasks.TodoList
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.authstarterkotlin.ui.theme.AuthStarterKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuthStarterKotlinTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text(text = "Taskes") },
                                backgroundColor = Color.Blue,
                                contentColor = Color.White,
                                elevation = 4.dp,
                            )
                        },
                        content = {
                            Column(
                                modifier = Modifier
                                    .background(color = Color.Black)
                                    .fillMaxSize()
                                    .padding(it)
                                    .padding(16.dp),

                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                TodoList()
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AuthStarterKotlinTheme {
        Greeting("Android")
    }
}