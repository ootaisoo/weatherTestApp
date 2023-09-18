package com.example.gismeteoapitestapp.model

sealed class ForecastState {
    object Empty : ForecastState()
    object Loading : ForecastState()
    class Success(val forecast: Forecast) : ForecastState()
    class Error(t: Throwable) : ForecastState()
}