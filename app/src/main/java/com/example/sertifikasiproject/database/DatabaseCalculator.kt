package com.example.sertifikasiproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sertifikasiproject.models.ResultModel

// membuat kelas untuk deklarasi  database dengan memasukkan versi dan tabel
@Database(entities = [ResultModel::class], version = 1)
abstract class DatabaseCalculator:RoomDatabase() {
    abstract fun dataAccess():DataObjek
}