package com.example.authstarterkotlin.student.domain.use_case

import com.example.authstarterkotlin.core.utils.DefaultUseCase
import com.example.authstarterkotlin.core.utils.Resource
import com.example.authstarterkotlin.student.domain.model.StudentModel
import com.example.authstarterkotlin.student.domain.repositery.StudentRepo
import kotlinx.coroutines.flow.Flow

class AddUseCase(
    private val studentRepository: StudentRepo
) {
    suspend operator fun invoke(studentModel: StudentModel): Flow<Resource<Unit>> {
        return DefaultUseCase(
            onRequest = {
                studentRepository.insertStudent(studentModel)
            }
        ).execute()
    }
}