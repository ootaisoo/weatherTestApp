package com.example.gismeteoapitestapp.data.repository

import com.example.gismeteoapitestapp.model.ForecastResponse

interface CachingRepository {
    fun copyToClipboard(forecast: ForecastResponse)
    fun saveToDownloads(forecast: ForecastResponse)
    fun saveRequestsInfo(requestsInfo: String)
    fun fetchRequestsInfo(): String
}