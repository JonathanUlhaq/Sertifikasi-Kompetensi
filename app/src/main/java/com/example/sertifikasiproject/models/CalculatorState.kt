package com.example.sertifikasiproject.models

import com.example.sertifikasiproject.utils.Aritmatika

//Tabel untuk patokan pengisian nomor dan operasi aritmatika
data class CalculatorState  (
    val number_1:String = "",
    val number_2:String = "",
    val aritmatika:Aritmatika? = null )