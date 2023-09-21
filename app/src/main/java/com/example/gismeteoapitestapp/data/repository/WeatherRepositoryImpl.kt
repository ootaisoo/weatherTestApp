package com.example.gismeteoapitestapp.data.repository

import com.example.gismeteoapitestapp.data.GismeteoApiService
import com.example.gismeteoapitestapp.model.Forecast
import com.example.gismeteoapitestapp.model.SearchResult
import retrofit2.Response

class WeatherRepositoryImpl(
    private val gisMeteoApiService: GismeteoApiService
) : WeatherRepository {

    override suspend fun searchId(location: String): Response<SearchResult> {
        return gisMeteoApiService.searchId(location)
    }

    override suspend fun requestForecast(id: Int): Response<Forecast> {
        return gisMeteoApiService.requestForecast(id)
    }
}