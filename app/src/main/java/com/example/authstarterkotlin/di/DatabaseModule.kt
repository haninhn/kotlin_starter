package com.example.authstarterkotlin.di

import com.example.authstarterkotlin.core.utils.ManageStudentDB
import android.app.Application
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication() as Application,
            ManageStudentDB::class.java,
            ManageStudentDB.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}
