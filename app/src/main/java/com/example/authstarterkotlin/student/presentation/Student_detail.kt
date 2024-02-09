package com.example.authstarterkotlin.student.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun StudentDetailScreen(
    navigation: NavController,
    username: String,
    cardname: String,
    divition: String,
) {
    val systemUiController = rememberSystemUiController()
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { androidx.compose.material.Text(text = "Students") },
                backgroundColor = Color(0xFF3757FF),
                contentColor = Color.White,
                elevation = 4.dp,
                navigationIcon = {
                    IconButton(onClick = { navigation.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Column(

            modifier = Modifier
                .background(color = Color(0xFFDCE9FF))
                .fillMaxSize()
                .padding(it)
                .padding(16.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Username: $username", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Division: $divition", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Card Name: $cardname", fontSize = 20.sp)
        }
    }

}