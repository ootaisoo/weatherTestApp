package com.example.gismeteoapitestapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.gismeteoapitestapp.di.MainComponent
import com.example.gismeteoapitestapp.interactor.WeatherInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Calendar
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private var weatherInteractor: WeatherInteractor
) : ViewModel() {

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

    fun requestWeather() {
        weatherInteractor.requestWeather()
    }
}