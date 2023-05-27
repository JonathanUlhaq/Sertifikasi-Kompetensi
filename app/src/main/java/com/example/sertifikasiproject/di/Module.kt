package com.example.sertifikasiproject.di

import android.content.Context
import android.provider.ContactsContract.Data
import androidx.room.Room
import com.example.sertifikasiproject.database.DataObjek
import com.example.sertifikasiproject.database.DatabaseCalculator
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@dagger.Module
class Module {

//    Membuat database
    @Provides
    @Singleton
    fun databaseProvider(@ApplicationContext context:Context):DatabaseCalculator =
        Room.databaseBuilder(
            context,
            DatabaseCalculator::class.java,
            "db_calculator"
        )
            .fallbackToDestructiveMigration()
            .build()

//    Membuat data akses objelk
    @Provides
    @Singleton
    fun databaseAccess(databaseCalculator: DatabaseCalculator):DataObjek =
        databaseCalculator.dataAccess()
}