package com.example.gismeteoapitestapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.gismeteoapitestapp.interactor.WeatherInteractor
import com.example.gismeteoapitestapp.model.Forecast
import com.example.gismeteoapitestapp.model.ForecastState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private var weatherInteractor: WeatherInteractor
) : ViewModel() {

    private val ioScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val calendar = Calendar.getInstance()
    private val _pickedDate: MutableStateFlow<Long> = MutableStateFlow(calendar.timeInMillis)
    val pickedDate: StateFlow<Long> = _pickedDate.asStateFlow()

    private val _forecastState: MutableStateFlow<ForecastState> = MutableStateFlow(ForecastState.Empty)
    val forecastState: StateFlow<ForecastState> = _forecastState.asStateFlow()

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

    fun requestWeather(location: String) {
        _forecastState.value = ForecastState.Loading
        ioScope.launch {
            weatherInteractor.requestForecast(location) { forecastResult ->
                forecastResult
                    .onSuccess {
                        _forecastState.value = ForecastState.Success(it)
                    }
                    .onFailure {
                        _forecastState.value = ForecastState.Error(it)
                    }
            }
        }
    }
}