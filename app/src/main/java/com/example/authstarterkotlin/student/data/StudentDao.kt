package com.example.authstarterkotlin.student.data
import com.example.authstarterkotlin.student.data.entities.StudentEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDao {
  @Insert()
  suspend fun insertStudent(student: StudentEntity)

  @Query("SELECT * FROM student_table")
  suspend fun getAllStudents(): List<StudentEntity>

  @Query("DELETE FROM student_table WHERE userName = :studentId")
  suspend fun deleteStudentById(studentId: String)

  @Update
  suspend fun updateStudent(student: StudentEntity)


}