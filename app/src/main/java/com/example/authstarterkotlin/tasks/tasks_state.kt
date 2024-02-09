package com.example.authstarterkotlin.tasks

import com.example.authstarterkotlin.student.domain.model.StudentModel

data class StudentState(
    val students: List<StudentModel> = emptyList(),
    val isLoading: Boolean = false,
    val success: Boolean = false,

    )