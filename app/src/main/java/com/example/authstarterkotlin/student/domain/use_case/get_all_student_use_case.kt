package com.example.authstarterkotlin.student.domain.use_case

import com.example.authstarterkotlin.core.utils.DefaultUseCase
import com.example.authstarterkotlin.core.utils.Resource
import com.example.authstarterkotlin.student.domain.model.StudentModel
import com.example.authstarterkotlin.student.domain.repositery.StudentRepo
import kotlinx.coroutines.flow.Flow

class GetAllStudentsUseCase(
    private val studentRepository: StudentRepo
) {
    suspend operator fun invoke(): Flow<Resource<List<StudentModel>>> {
        return DefaultUseCase(
            onRequest = {
                studentRepository.getAllStudents()
            }
        ).execute()
    }
}