package com.example.authstarterkotlin.core.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.USER
    ) {
        userNavGraph(navController = navController)
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val USER = "student_graph"
}