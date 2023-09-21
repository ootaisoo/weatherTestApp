package com.example.gismeteoapitestapp.interactor

import com.example.gismeteoapitestapp.model.Forecast

interface ForecastInteractor {
    suspend fun requestForecast(location: String, onResult: (Result<Forecast>) -> Unit)
    fun copyToClipboard(text: String)
    fun saveToDisk(text: String)
}