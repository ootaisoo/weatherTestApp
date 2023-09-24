package com.example.gismeteoapitestapp.interactor

import com.example.gismeteoapitestapp.model.Forecast
import com.example.gismeteoapitestapp.model.ForecastResponse

interface ForecastInteractor {
    suspend fun requestForecast(location: String, onResult: (Result<Forecast>) -> Unit)
    fun copyToClipboard(forecast: ForecastResponse)
    fun saveToDisk(forecast: ForecastResponse)
}