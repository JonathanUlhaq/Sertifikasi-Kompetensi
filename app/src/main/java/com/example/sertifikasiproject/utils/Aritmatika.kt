package com.example.sertifikasiproject.utils

// Untuk mendaftarkan nilai constant tiap operasi aritmatika
sealed class Aritmatika(val symbol:String) {
    object Plus:Aritmatika("+")
    object Minus:Aritmatika("-")
    object Divide:Aritmatika("/")
    object Multiple:Aritmatika("*")
    object Modulo:Aritmatika("%")
}
