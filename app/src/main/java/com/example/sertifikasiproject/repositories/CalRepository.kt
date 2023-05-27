package com.example.sertifikasiproject.repositories

import androidx.room.Dao
import com.example.sertifikasiproject.database.DataObjek
import com.example.sertifikasiproject.models.ResultModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Mengakses data objek menggunakan pattern MVVM
class CalRepository @Inject constructor(private val dao: DataObjek){
    fun getAllData():Flow<List<ResultModel>> = dao.getAllData()
    suspend fun insertData(data:ResultModel) = dao.insertData(data)
}