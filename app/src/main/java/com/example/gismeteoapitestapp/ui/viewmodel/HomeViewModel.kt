package com.example.gismeteoapitestapp.ui.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.gismeteoapitestapp.interactor.ForecastInteractor
import com.example.gismeteoapitestapp.model.Forecast
import com.example.gismeteoapitestapp.model.ForecastResponse
import com.example.gismeteoapitestapp.model.ForecastState
import com.example.gismeteoapitestapp.model.InvalidDateError
import com.example.gismeteoapitestapp.ui.router.MainRouter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val forecastInteractor: ForecastInteractor,
    private var router: MainRouter,
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
            forecastInteractor.requestForecast(location) { forecastResult ->
                forecastResult
                    .onSuccess { forecast ->
                        _forecastState.value = forecast
                            .filterDate()
                            ?.let { ForecastState.Success(it) }
                            ?: ForecastState.Error(InvalidDateError())
                    }
                    .onFailure { throwable ->
                        _forecastState.value = ForecastState.Error(throwable)
                    }
            }
        }
    }

    fun copyToClipboard(forecast: ForecastResponse) {
        forecastInteractor.copyToClipboard(forecast)
    }

    fun saveToDisk(forecast: ForecastResponse) {
        ioScope.launch {
            forecastInteractor.saveToDisk(forecast)
        }
    }

    fun showLog(t: Throwable) {
        router.showErrorsLog(t)
    }

    fun onStartFragment(fragment: Fragment) = router.onStartFragment(fragment)

    private fun Forecast.filterDate(): ForecastResponse? {
        return response.find {
            val pickedDate = Date(pickedDate.value)
            val forecastDate = Date(it.date.unix.toLong() * 1000)
            pickedDate.toDateWithoutTime()?.compareTo(forecastDate.toDateWithoutTime()) == 0
        }
    }

    private fun Date.toDateWithoutTime(): Date? {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.parse(sdf.format(this))
    }
}