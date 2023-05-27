package com.example.sertifikasiproject.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sertifikasiproject.models.CalculatorState
import com.example.sertifikasiproject.models.ResultModel
import com.example.sertifikasiproject.repositories.CalRepository
import com.example.sertifikasiproject.utils.ActionCal
import com.example.sertifikasiproject.utils.Aritmatika
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//Otakny dari setiap operasi di UI
@HiltViewModel
class CalViewModel @Inject constructor(private val repository: CalRepository) : ViewModel() {
    var state by mutableStateOf(CalculatorState())
    private val _uiState = MutableStateFlow<List<ResultModel>>(emptyList())
    val uiState = _uiState.asStateFlow()

//    Menampilkan semua history
    fun getAllData() =
        viewModelScope.launch {
            repository.getAllData().collect {
                if (it.isNotEmpty()) _uiState.value = it else _uiState.value = emptyList()
            }
        }

    private fun insertData(data:ResultModel) =
        viewModelScope.launch {
            repository.insertData(data)
        }

//    Otomisasi pemanggilan function
    init {
        getAllData()
    }

    //    Fungsi untuk mengetahui aksi yang diinputkan dari UI
    fun onAction(action: ActionCal) {
        when (action) {
            is ActionCal.Clear -> state = CalculatorState()
            is ActionCal.Decimal -> decimalFunction()
            is ActionCal.Equal -> equalFunction()
            is ActionCal.Erase -> eraseFunction()
            is ActionCal.Number -> numberFunction(action.number)
            is ActionCal.Operation -> operationFunction(action.aritmatika)
            null -> return
        }
    }

//     Menginputkan operasi pada data model
    private fun operationFunction(aritmatika: Aritmatika) {
        state = state.copy(
            aritmatika = aritmatika
        )
    }

    //    Fungsi untuk mengiinputkan nomor dari UI
    private fun numberFunction(number: String) {
        if (state.aritmatika == null) {
            state =  state.copy(
                number_1 = state.number_1 + number
            )
        } else {
           state = state.copy(number_2 = state.number_2 + number)
        }
    }

    //    Fungsi untuk melakukan penghapusan dengan memperhatikan beberepa perkondisian
    private fun eraseFunction() {
        if (state.number_2.isNotEmpty()) {
            state = state.copy(
                number_2 = state.number_2.dropLast(1)
            )
            return
        }
        if (state.aritmatika != null) {
            state = state.copy(
                aritmatika = null
            )
            return
        }
        if (state.number_1.isNotEmpty()) {
            state = state.copy(
                number_1 = state.number_1.dropLast(1)
            )
        }
    }

    //    Fungsi untuk menjumlah angka sesuai operator
    private fun equalFunction() {

        val getNumber_1 = state.number_1.toDoubleOrNull()
        val getNumber_2 = state.number_2.toDoubleOrNull()
        if (getNumber_1 != null && getNumber_2 != null) {
            val result = when (state.aritmatika) {
                is Aritmatika.Plus -> getNumber_1 + getNumber_2
                is Aritmatika.Divide ->    getNumber_1 / getNumber_2
                is Aritmatika.Minus -> getNumber_1 - getNumber_2
                is Aritmatika.Multiple -> getNumber_1 * getNumber_2
                is Aritmatika.Modulo -> getNumber_1 % getNumber_2
                null -> return
            }
            if (result.toString() == "NaN" || result.toString() == "Infinity") {
                state = state.copy(
                    number_1 = "",
                    number_2 = "",
                    aritmatika = null
                )
            } else {
                state = state.copy(
                    number_1 = result.toString().take(8),
                    number_2 = "",
                    aritmatika = null
                )
                insertData(ResultModel(result = result.toString().take(8) ))
            }



        }
    }

    //    Fungsi untuk input desimal
    private fun decimalFunction() {
        if (state.number_1.isNotEmpty() && state.aritmatika == null && !state.number_1.contains(".")) {
            state = state.copy(
                number_1 = state.number_1 + "."
            )
        }
        if (state.number_2.isNotEmpty() && !state.number_2.contains(".")) {
            state = state.copy(
                number_2 = state.number_2 + "."
            )
        }
    }

}