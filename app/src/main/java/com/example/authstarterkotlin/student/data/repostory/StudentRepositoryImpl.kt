package com.example.authstarterkotlin.student.data.repostory

import com.example.authstarterkotlin.student.data.LocalDataSource
import com.example.authstarterkotlin.student.data.mapper.toStudentEntity
import com.example.authstarterkotlin.student.data.mapper.toStudentModel
import com.example.authstarterkotlin.student.domain.model.StudentModel
import com.example.authstarterkotlin.student.domain.repositery.StudentRepo

class StudentRepositoryImpl(
    private val studentLocalDataSource: LocalDataSource
) : StudentRepo {

    override suspend fun getAllStudents(): List<StudentModel> {
        return studentLocalDataSource.getStudents().map {
            it.toStudentModel()
        }
    }

    override suspend fun updateStudent(student: StudentModel) {
        val studentEntity = student.toStudentEntity()
        studentLocalDataSource.updateStudent(studentEntity)
    }


    override suspend fun deleteStudent(id: String) {
        studentLocalDataSource.deleteStudent(id)
    }

    override suspend fun insertStudent(student: StudentModel) {
        val studentEntity = student.toStudentEntity()
        studentLocalDataSource.insertStudent(studentEntity)
    }
}
