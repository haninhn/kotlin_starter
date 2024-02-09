package com.example.authstarterkotlin.student.presentation.student_screen

sealed class StudentEvent{
    data class DeleteBtn(val userName: String) : StudentEvent()


}
