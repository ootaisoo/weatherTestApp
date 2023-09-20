package com.example.gismeteoapitestapp.model

sealed class ForecastState {
    object Empty : ForecastState()
    object Loading : ForecastState()
    class Success(val forecast: ForecastResponse) : ForecastState()
    class Error(val t: Throwable) : ForecastState()
}