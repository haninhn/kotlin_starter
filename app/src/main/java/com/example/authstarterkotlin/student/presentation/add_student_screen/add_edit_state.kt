package com.example.authstarterkotlin.student.presentation.add_student_screen

import com.example.authstarterkotlin.student.domain.model.StudentModel

data class AddEditState(
    val userName: String  ="",
    val card: String ="",
    val division: String ="",
    val userNameError: String = "",
    val cardError: String= "",
    val divisionError: String  = "",
    val isLoading: Boolean = false,
    val successAdd: Boolean = false,
    val students: List<StudentModel> = emptyList()

    )
