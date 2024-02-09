package com.example.authstarterkotlin.core.navigation
sealed class Screen(val route: String) {
    object AddEditStudent : Screen("add_edit_student_screen")
    object StudentScreen : Screen("student_screen")
    object AllStudentScreen : Screen("AllStudent_Screen")

}