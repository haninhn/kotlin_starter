package com.example.authstarterkotlin.student.presentation.student_screen

import com.example.authstarterkotlin.student.domain.model.StudentModel

data class StudentState(
val students: List<StudentModel> = emptyList(),
val isLoading: Boolean = false,
val success: Boolean = false,

)