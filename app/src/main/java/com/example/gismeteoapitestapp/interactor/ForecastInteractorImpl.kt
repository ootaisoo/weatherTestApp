package com.example.gismeteoapitestapp.interactor

import com.example.gismeteoapitestapp.model.Forecast
import com.example.gismeteoapitestapp.model.ResponseCode.toException
import com.example.gismeteoapitestapp.repository.CachingRepository
import com.example.gismeteoapitestapp.repository.WeatherRepository
import retrofit2.Response

class ForecastInteractorImpl(
    private val weatherRepository: WeatherRepository,
    private val cachingRepository: CachingRepository
) : ForecastInteractor {

    override suspend fun requestForecast(location: String, onResult: (Result<Forecast>) -> Unit) {
        weatherRepository.searchId(location)
            .toKotlinResult()
            .onSuccess {
                val locationId = it.response.items[0].id
                val response = weatherRepository.requestForecast(locationId)
                response.toKotlinResult()
                    .onSuccess { forecast ->
                        onResult(Result.success(forecast))
                    }.onFailure { throwable ->
                        onResult(Result.failure(throwable))
                    }
            }
            .onFailure {
                onResult(Result.failure(it))
            }
    }

    override fun copyToClipboard(text: String) {
        cachingRepository.copyToClipboard(text)
    }

    override fun saveToDisk(text: String) {
        cachingRepository.saveToDownloads(text)
    }
}

inline fun <reified T> Response<T>.toKotlinResult(): Result<T> {
    return try {
        if (isSuccessful && (this.body() != null)) {
            Result.success(body() as T)
        } else {
            val message = message().takeIf { !it.isNullOrEmpty() } ?: errorBody()?.string()
            Result.failure(code().toException(message))
        }
    } catch (e: Throwable) {
        Result.failure(e)
    }
}
