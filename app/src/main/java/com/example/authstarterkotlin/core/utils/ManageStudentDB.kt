package com.example.authstarterkotlin.core.utils

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.authstarterkotlin.student.data.StudentDao
import com.example.authstarterkotlin.student.data.entities.StudentEntity

@Database(
    entities = [StudentEntity::class],
    version =1,
    exportSchema = false
)
abstract class ManageStudentDB : RoomDatabase() {
  abstract val studentDao: StudentDao

  companion object {
    const val DATABASE_NAME = "students_db"
  }
}