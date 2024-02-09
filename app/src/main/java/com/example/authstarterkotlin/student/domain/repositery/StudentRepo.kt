package com.example.authstarterkotlin.student.domain.repositery

import com.example.authstarterkotlin.student.domain.model.StudentModel

interface StudentRepo {
    suspend fun getAllStudents(): List<StudentModel>
    suspend fun updateStudent(student: StudentModel)
    suspend fun deleteStudent(id: String)
    suspend fun insertStudent(student: StudentModel)
}