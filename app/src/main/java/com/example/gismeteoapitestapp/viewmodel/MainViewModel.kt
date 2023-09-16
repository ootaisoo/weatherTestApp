package com.example.gismeteoapitestapp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Calendar

class MainViewModel : ViewModel() {

    private val calendar = Calendar.getInstance()
    private val _pickedDate: MutableStateFlow<Long> = MutableStateFlow(calendar.timeInMillis)
    val pickedDate: StateFlow<Long> = _pickedDate.asStateFlow()

    fun pickDate(year: Int, month: Int, day: Int) {
        _pickedDate.update {
            calendar.apply {
                set(Calendar.YEAR, year)
                set(Calendar.MONTH, month)
                set(Calendar.DAY_OF_MONTH, day)
            }
            .timeInMillis
        }
    }
}