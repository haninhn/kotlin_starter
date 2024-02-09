package com.example.authstarterkotlin.student.data.mapper

import com.example.authstarterkotlin.student.data.entities.StudentEntity
import com.example.authstarterkotlin.student.domain.model.StudentModel

fun StudentEntity.toStudentModel(): StudentModel {
    return StudentModel(
     userName= userName,
     card= card,
     division= division,
    )
}
fun StudentModel.toStudentEntity(): StudentEntity {
    return StudentEntity(
        userName= userName,
        card= card,
        division= division,
    )
}


