package com.example.sertifikasiproject

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sertifikasiproject.database.DataObjek
import com.example.sertifikasiproject.database.DatabaseCalculator
import com.example.sertifikasiproject.models.ResultModel
import dagger.hilt.InstallIn
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

// Small test untuk testing pada database
@InstallIn(AndroidJUnit4::class)
class DatabaseTesting {

    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    lateinit var dao:DataObjek
    lateinit var database:DatabaseCalculator

    @Before
    fun setup () {
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),DatabaseCalculator::class.java).build()
        dao = database.dataAccess()
    }

    @After
    fun finish() {
        database.close()
    }

//    Test untuk mengetahui apakah database bisa menyimpan dan menampilkan nilai
    @Test
    fun insertDatabase() {
        runBlockingTest {
            dao.insertData(ResultModel(result = "6"))
            dao.getAllData()
        }
    }

}