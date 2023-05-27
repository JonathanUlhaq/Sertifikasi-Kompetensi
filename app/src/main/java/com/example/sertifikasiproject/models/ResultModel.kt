package com.example.sertifikasiproject.models

import androidx.room.Entity
import androidx.room.PrimaryKey

//Tabel hasil untuk disimpan di database
@Entity(tableName = "tb_result")
data class ResultModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val result:String = ""
)
