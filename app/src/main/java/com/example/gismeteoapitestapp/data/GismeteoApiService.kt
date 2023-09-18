package com.example.gismeteoapitestapp.data

import com.example.gismeteoapitestapp.model.Forecast
import com.example.gismeteoapitestapp.model.SearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface GismeteoApiService {

    companion object {
        const val GISMETEO_BASE_URL = "https://api.gismeteo.net/v2/"
    }

    @GET("/search/cities/")
    @Headers("X-Gismeteo-Token: cb255.344307")
    suspend fun searchId(@Query("query") location: String): Response<SearchResult>

    @GET("/weather/current/{id}/")
    @Headers("X-Gismeteo-Token: cb255.344307")
    suspend fun requestForecast(@Path("id") id: Int): Response<Forecast>
}