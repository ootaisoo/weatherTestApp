package com.example.gismeteoapitestapp.interactor

import com.example.gismeteoapitestapp.model.Forecast

interface WeatherInteractor {
    suspend fun requestForecast(location: String, onResult: (Result<Forecast>) -> Unit)
}