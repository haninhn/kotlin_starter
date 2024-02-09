package com.example.authstarterkotlin.core.graphs
import AddEditStudent
import com.example.authstarterkotlin.student.presentation.student_screen.ListStudent
import com.example.authstarterkotlin.student.presentation.StudentDetailScreen
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.authstarterkotlin.core.navigation.Screen

fun NavGraphBuilder.userNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.USER,
        startDestination = Screen.AllStudentScreen.route
    ) {
        composable(route = Screen.AddEditStudent.route+"/{username}/{card}/{division}",
            arguments = listOf(
                navArgument(
                    name = "username"
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                },
                    navArgument(
                        name = "card"
                    ) {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(
                    name = "division"
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                },
            )


        ) { it ->
            AddEditStudent(
                navigation = navController,
                 id =  it.arguments?.getString("username")!!,

            )
        }
        composable(Screen.AddEditStudent.route)
        {
            AddEditStudent(
                navigation = navController,
                id = "",
                )
        }

        composable(Screen.AllStudentScreen.route)
        {
            ListStudent(navigation = navController)
        }

        composable(Screen.StudentScreen.route+"/{username}/{card}/{division}",
            arguments = listOf(
            navArgument(
                name = "username"
            ) {
                type = NavType.StringType
                defaultValue = ""
            },
            navArgument(
                name = "card"
            ) {
                type = NavType.StringType
                defaultValue = ""
            },
            navArgument(
                name = "division"
            ) {
                type = NavType.StringType
                defaultValue = ""
            },

        )) {entry ->
            StudentDetailScreen(
                navigation = navController,
                username =  entry.arguments?.getString("username")!!,
                cardname  =  entry.arguments?.getString ("card")!!,
                divition  =  entry.arguments?.getString ("division")!!

            )
        }
    }
}