package com.example.sertifikasiproject

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Deklarasi untuk mengetahui Aplikasi ini menggunakan hilt - dagger sebagai injeksi
@HiltAndroidApp
class CalculatorApps:Application()