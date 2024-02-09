package com.example.authstarterkotlin.student.presentation.student_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authstarterkotlin.core.utils.Resource
import com.example.authstarterkotlin.core.utils.ValidationEvent
import com.example.authstarterkotlin.core.utils.validation.validateFiled
import com.example.authstarterkotlin.student.domain.model.StudentModel
import com.example.authstarterkotlin.student.domain.use_case.AddUseCase
import com.example.authstarterkotlin.student.domain.use_case.DeleteUseCase
import com.example.authstarterkotlin.student.domain.use_case.EditUseCase
import com.example.authstarterkotlin.student.domain.use_case.GetAllStudentsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class StudentsViewModel(
    private val deleteUseCase: DeleteUseCase,
    private val getAllStudentsUseCase: GetAllStudentsUseCase) :
    ViewModel() {
    private val _state = mutableStateOf(StudentState())
     val state: State<StudentState> = _state

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()
   init {
       getStudentsData()
   }
    fun onEvent(event: StudentEvent) {
        when (event) {
            is StudentEvent.DeleteBtn -> {
                deleteStudent(event.userName)
            }

        }
    }

    private fun deleteStudent(userName: String) {
        viewModelScope.launch {
            deleteUseCase.invoke(userName)
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                success = false,
                            )
                        }
                        is Resource.Success -> {
                            val updatedStudents = state.value.students.filter { it.userName != userName }
                            _state.value = state.value.copy(
                                success = true,
                                students = updatedStudents

                            )

                        }

                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                isLoading = false,

                                )

                        }
                    }
                }
        }
    }

    private fun getStudentsData() {
        viewModelScope.launch {
            getAllStudentsUseCase.invoke()
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                isLoading = true,
                            )
                        }
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                isLoading = false,
                                students = result.data!!
                            )
                        }

                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                isLoading = false,


                            )

                        }
                    }
                }
        }
    }
}