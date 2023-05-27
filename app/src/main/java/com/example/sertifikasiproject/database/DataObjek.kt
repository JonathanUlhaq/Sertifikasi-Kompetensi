package com.example.sertifikasiproject.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sertifikasiproject.models.ResultModel
import kotlinx.coroutines.flow.Flow

// Query untuk mengakses  database
@Dao
interface DataObjek {
    @Query("SELECT * FROM tb_result")
    fun getAllData():Flow<List<ResultModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data:ResultModel)
}