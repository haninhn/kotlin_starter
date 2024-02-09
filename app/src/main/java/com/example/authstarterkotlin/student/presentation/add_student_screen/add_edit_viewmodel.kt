package com.example.authstarterkotlin.student.presentation.add_student_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authstarterkotlin.core.utils.Resource
import com.example.authstarterkotlin.core.utils.ValidationEvent
import com.example.authstarterkotlin.core.utils.validation.validateFiled
import com.example.authstarterkotlin.student.domain.model.StudentModel
import com.example.authstarterkotlin.student.domain.use_case.AddUseCase
import com.example.authstarterkotlin.student.domain.use_case.EditUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditeViewModel(private val editUseCase: EditUseCase, private val addUseCase: AddUseCase,
                        savedStateHandle: SavedStateHandle,

                        ) : ViewModel() {
    private val _state = mutableStateOf(AddEditState())
    val state: State<AddEditState> = _state
    init {

        val username = savedStateHandle.get<String>("username")
        val card = savedStateHandle.get<String>("card")
        val division = savedStateHandle.get<String>("division")

        if(username != null){
            _state.value = state.value.copy(
                userName = username,
                card = card!!,
                division = division!!,
            )
        }

    }


    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: AddEditEvent) {
        when (event) {
            is AddEditEvent.CardChanged -> {
                _state.value = state.value.copy(card = event.card)
            }
            is AddEditEvent.UserNameChanged -> {
                _state.value = state.value.copy(userName = event.userName)
            }
            is AddEditEvent.DivisionChanged -> {
                _state.value = state.value.copy(division = event.division)
            }

            is AddEditEvent.SubmitBtn -> {
                val cardResult = validateFiled(_state.value.card)
                val userNameResult = validateFiled(_state.value.userName)
                val divisionResult = validateFiled(_state.value.division)

                val hasError = listOf(
                    cardResult,
                    userNameResult,
                    divisionResult
                ).any { !it.successful }

                if (cardResult.errorMessage != null) {
                    _state.value = state.value.copy(
                        cardError = cardResult.errorMessage
                    )
                }

                if (userNameResult.errorMessage != null) {
                    _state.value = state.value.copy(
                        userNameError = userNameResult.errorMessage
                    )
                }

                if (divisionResult.errorMessage != null) {
                    _state.value = state.value.copy(
                        divisionError = divisionResult.errorMessage
                    )
                }


                if (hasError) {
                    return
                }else{
                    submitData(_state.value.card, _state.value.userName, state.value.division)
                }
            }
              is AddEditEvent.UpdateBtn -> {
                  val cardResult = validateFiled(_state.value.card)
                  val userNameResult = validateFiled(_state.value.userName)
                  val divisionResult = validateFiled(_state.value.division)

                  val hasError = listOf(
                      cardResult,
                      userNameResult,
                      divisionResult
                  ).any { !it.successful }

                  if (cardResult.errorMessage != null) {
                      _state.value = state.value.copy(
                          cardError = cardResult.errorMessage
                      )
                  }

                  if (userNameResult.errorMessage != null) {
                      _state.value = state.value.copy(
                          userNameError = userNameResult.errorMessage
                      )
                  }

                  if (divisionResult.errorMessage != null) {
                      _state.value = state.value.copy(
                          divisionError = divisionResult.errorMessage
                      )
                  }


                  if (hasError) {
                      return
                  }else{
                      editeStudent(_state.value.card, _state.value.userName, state.value.division)
                  }

             }
            else -> {}
        }
    }

    private fun editeStudent(card: String, userName: String, division: String) {
        viewModelScope.launch {
            editUseCase.invoke(studentModel = StudentModel(card = card, userName = userName, division = division))
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                isLoading = true,
                                successAdd =false
                            )
                        }
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                isLoading = false,
                                successAdd =true

                            )
                            Log.e("add", "add: ${result.message}")
                        }

                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                isLoading = false,
                                successAdd =false

                            )
                            Log.e("add", "lplpl")

                        }
                        else -> {}
                    }
                }
        }

    }

    private fun submitData(card : String, username: String, division: String) {
        viewModelScope.launch {
            addUseCase.invoke(studentModel = StudentModel(card = card, userName = username, division = division))
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                isLoading = true,
                                successAdd =false
                            )
                        }
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                isLoading = false,
                                successAdd =true

                            )
                            Log.e("add", "add: ${result.message}")
                        }

                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                isLoading = false,
                                successAdd =false

                            )
                            Log.e("add", "lplpl")

                        }
                        else -> {}
                    }
                }
        }
    }
}