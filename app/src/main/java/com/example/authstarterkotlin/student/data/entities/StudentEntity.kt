package com.example.authstarterkotlin.student.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class StudentEntity(
    @PrimaryKey
    val userName: String,
    val card: String,
    val division: String,
)

