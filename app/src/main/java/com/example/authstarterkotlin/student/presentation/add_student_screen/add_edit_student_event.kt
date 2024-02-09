package com.example.authstarterkotlin.student.presentation.add_student_screen

sealed class AddEditEvent {
    data class UserNameChanged(val userName: String) : AddEditEvent()
    data class CardChanged(val card: String) : AddEditEvent()
    data class AgeChanged(val age: String) : AddEditEvent()
    data class DivisionChanged(val division: String) : AddEditEvent()
    object SubmitBtn : AddEditEvent()
    object UpdateBtn : AddEditEvent()

}