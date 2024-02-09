package com.example.authstarterkotlin.student.data

import com.example.authstarterkotlin.student.data.entities.StudentEntity

class LocalDataSource(private var dao: StudentDao) {
    
    suspend fun insertStudent(studentEntity: StudentEntity) {
        return dao.insertStudent(studentEntity)
    }

    suspend fun getStudents(): List<StudentEntity> {
        return dao.getAllStudents()
    }

    suspend fun deleteStudent(id: String){
        return dao.deleteStudentById(id)
    }
    suspend fun updateStudent(studentEntity: StudentEntity){
        return dao.updateStudent(studentEntity)
    }

}