package com.example.gismeteoapitestapp.repository

import com.example.gismeteoapitestapp.model.Forecast
import com.example.gismeteoapitestapp.model.SearchResult
import retrofit2.Response

interface WeatherRepository {
    suspend fun searchId(location: String): Response<SearchResult>
    suspend fun requestForecast(id: Int): Response<Forecast>
}