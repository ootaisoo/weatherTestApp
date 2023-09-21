package com.example.gismeteoapitestapp.data

import com.example.gismeteoapitestapp.data.repository.CachingRepository
import okhttp3.logging.HttpLoggingInterceptor

class CustomLogger(
    private val cachingRepository: CachingRepository
) : HttpLoggingInterceptor.Logger {

    override fun log(message: String) {
        cachingRepository.saveRequestsInfo(message)
    }
}