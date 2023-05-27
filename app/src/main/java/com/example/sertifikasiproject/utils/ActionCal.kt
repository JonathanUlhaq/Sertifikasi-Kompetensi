package com.example.sertifikasiproject.utils

//Sealed class untuk mendaftarkan nilai constant tiap Action
sealed class ActionCal {
    data class  Number(val number:String):ActionCal()
    data class Operation(val aritmatika: Aritmatika):ActionCal()
    object Clear:ActionCal()
    object Equal:ActionCal()
    object Erase:ActionCal()
    object Decimal:ActionCal()
}
