package com.example.gismeteoapitestapp.data

import com.example.gismeteoapitestapp.data.GismeteoApiService.Companion.GISMETEO_BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun getInstance(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(GISMETEO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}