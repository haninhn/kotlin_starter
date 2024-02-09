package com.example.authstarterkotlin.di
import com.example.authstarterkotlin.core.utils.ManageStudentDB
import com.example.authstarterkotlin.student.data.LocalDataSource
import com.example.authstarterkotlin.student.data.StudentDao
import com.example.authstarterkotlin.student.data.repostory.StudentRepositoryImpl
import com.example.authstarterkotlin.student.domain.repositery.StudentRepo
import com.example.authstarterkotlin.student.domain.use_case.AddUseCase
import com.example.authstarterkotlin.student.domain.use_case.DeleteUseCase
import com.example.authstarterkotlin.student.domain.use_case.EditUseCase
import com.example.authstarterkotlin.student.domain.use_case.GetAllStudentsUseCase
import com.example.authstarterkotlin.student.presentation.add_student_screen.AddEditeViewModel
import com.example.authstarterkotlin.student.presentation.student_screen.StudentsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val studentModule = module {
    single<StudentDao> {
        val database = get<ManageStudentDB>()
        database.studentDao
    }
    single { LocalDataSource(dao = get()) }


    single<StudentRepo> {
        StudentRepositoryImpl(studentLocalDataSource = get())
    }

    single { GetAllStudentsUseCase(studentRepository = get()) }
    single { AddUseCase(studentRepository = get()) }
    single { EditUseCase(studentRepository = get()) }
    single { DeleteUseCase(studentRepository = get()) }

    viewModel { AddEditeViewModel(editUseCase = get(), addUseCase = get(),  savedStateHandle= get())}

    viewModel { StudentsViewModel(getAllStudentsUseCase = get(), deleteUseCase = get()) }

}