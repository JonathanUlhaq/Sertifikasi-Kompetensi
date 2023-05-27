package com.example.sertifikasiproject.view

import android.hardware.lights.Light
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sertifikasiproject.utils.ActionCal
import com.example.sertifikasiproject.utils.Aritmatika
import com.example.sertifikasiproject.viewmodels.CalViewModel

// Fungsi untuk menampilkan composable Calculator pada UI
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CalculatorKeyboard(
    modifier: Modifier = Modifier,
    vm:CalViewModel
) {
    val showHistory = remember {
        mutableStateOf(false)
    }
    val uiState = vm.uiState.collectAsState().value
        Column(
            modifier = modifier
                .fillMaxHeight()
                .wrapContentHeight(Bottom)
        ) {

            Box(modifier = Modifier
                .padding(14.dp)) {
                Text(text = vm.state.number_1 + " " + (vm.state.aritmatika?.symbol?:"")+ " " +vm.state.number_2)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Surface(
                shape = RoundedCornerShape(12.dp),
                onClick = {
                    showHistory.value = !showHistory.value
                },
                modifier = modifier
                    .padding(14.dp),
                color = Color.LightGray.copy(0.2f)
            ) {
                Text(text = "Riwayat",
                    modifier = modifier
                        .padding(start = 12.dp, end = 12.dp, top = 6.dp, bottom = 6.dp))
            }
            Spacer(modifier = Modifier.height(12.dp))
           AnimatedVisibility(visible = !showHistory.value ) {
               Surface(
                   color = Color.LightGray.copy(0.2f)
               ) {
                   Column(
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(12.dp)
                   ) {
                       Row(
                           modifier = Modifier
                               .fillMaxWidth(),
                           horizontalArrangement = Arrangement.SpaceBetween,
                           verticalAlignment = Alignment.CenterVertically
                       ) {
                           CalculatorButton(text = "C") {
                               vm.onAction(ActionCal.Clear)
                           }
                           CalculatorButton(text = "%") {
                               vm.onAction(ActionCal.Operation(Aritmatika.Modulo))
                           }
                           CalculatorButton(text = "<") {
                               vm.onAction(ActionCal.Erase)
                           }
                           CalculatorButton(text = "/") {
                               vm.onAction(ActionCal.Operation(Aritmatika.Divide))
                               Log.d("STTTTT",  vm.state.number_1 + (vm.state.aritmatika?:"")+vm.state.number_2)
                           }
                       }
                       Spacer(modifier = Modifier.height(8.dp))
                       Row(
                           modifier = Modifier
                               .fillMaxWidth(),
                           horizontalArrangement = Arrangement.SpaceBetween,
                           verticalAlignment = Alignment.CenterVertically
                       ) {
                           CalculatorButton(text = "7") {
                               vm.onAction(ActionCal.Number("7"))
                           }
                           CalculatorButton(text = "8") {
                               vm.onAction(ActionCal.Number("8"))
                           }
                           CalculatorButton(text = "9") {
                               vm.onAction(ActionCal.Number("9"))
                           }
                           CalculatorButton(text = "x") {
                               vm.onAction(ActionCal.Operation(Aritmatika.Multiple))
                           }
                       }
                       Spacer(modifier = Modifier.height(8.dp))
                       Row(
                           modifier = Modifier
                               .fillMaxWidth(),
                           horizontalArrangement = Arrangement.SpaceBetween,
                           verticalAlignment = Alignment.CenterVertically
                       ) {
                           CalculatorButton(text = "4") {
                               vm.onAction(ActionCal.Number("4"))
                           }
                           CalculatorButton(text = "5") {
                               vm.onAction(ActionCal.Number("5"))
                           }
                           CalculatorButton(text = "6") {
                               vm.onAction(ActionCal.Number("6"))
                           }
                           CalculatorButton(text = "-") {
                               vm.onAction(ActionCal.Operation(Aritmatika.Minus))
                           }
                       }
                       Spacer(modifier = Modifier.height(8.dp))
                       Row(
                           modifier = Modifier
                               .fillMaxWidth(),
                           horizontalArrangement = Arrangement.SpaceBetween,
                           verticalAlignment = Alignment.CenterVertically
                       ) {
                           CalculatorButton(text = "1") {
                               vm.onAction(ActionCal.Number("1"))
                           }
                           CalculatorButton(text = "2") {
                               vm.onAction(ActionCal.Number("2"))
                           }
                           CalculatorButton(text = "3") {
                               vm.onAction(ActionCal.Number("3"))
                           }
                           CalculatorButton(text = "+") {
                               vm.onAction(ActionCal.Operation(Aritmatika.Plus))
                           }
                       }
                       Spacer(modifier = Modifier.height(8.dp))
                       Row(
                           modifier = Modifier
                               .fillMaxWidth(),
                           horizontalArrangement = Arrangement.SpaceBetween,
                           verticalAlignment = Alignment.CenterVertically
                       ) {
                           CalculatorButton(text = "0") {
                               vm.onAction(ActionCal.Number("0"))
                           }
                           CalculatorButton(text = ",") {
                               vm.onAction(ActionCal.Decimal)
                           }
                           CalculatorButton(text = "=") {
                               vm.onAction(ActionCal.Equal)
                           }
                       }
                   }
               }
           }

            AnimatedVisibility(visible = showHistory.value) {
                Surface(
                    color = Color.LightGray.copy(0.2f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        LazyColumn(content = {
                            items(uiState) {
                                Surface(
                                    shape = CircleShape,
                                    color = Color.Transparent,
                                    onClick = {
                                        vm.state = vm.state.copy(
                                            number_1 = it.result,
                                            number_2 = "",
                                            aritmatika = null
                                        )
                                        showHistory.value = false
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(text = it.result,modifier = Modifier
                                        .padding(6.dp))
                                }
                                Spacer(modifier = Modifier.height(12.dp))
                            }
                        })
                    }
                }
            }
        }

}

// Fungsi untuk menampilkan composable button pada UI
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CalculatorButton(
    text:String,
    onClick:() -> Unit
) {
    Surface(
        shape = CircleShape,
        color = Color.Transparent,
        onClick = {
            onClick.invoke()
        }
    ) {
        Text(text = text,
            modifier = Modifier
                .padding(8.dp),
            fontSize = 14.sp)
    }
}