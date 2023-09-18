package com.example.gismeteoapitestapp.interactor

import com.example.gismeteoapitestapp.model.Forecast
import com.example.gismeteoapitestapp.model.ResponseCode.toException
import com.example.gismeteoapitestapp.repository.WeatherRepository
import retrofit2.Response

class WeatherInteractorImpl(
    private val weatherRepository: WeatherRepository
) : WeatherInteractor {

    override suspend fun requestForecast(location: String, onResult: (Result<Forecast>) -> Unit) {
        weatherRepository.searchId(location)
            .toKotlinResult()
            .onSuccess {
                weatherRepository.requestForecast(it.id)
                    .toKotlinResult()
                    .onSuccess {
                        onResult(Result.success(it))
                    }.onFailure {
                        onResult(Result.failure(it))
                    }
            }
            .onFailure {
                onResult(Result.failure(it))
            }
    }
}

inline fun <reified T> Response<T>.toKotlinResult(): Result<T> {
    return try {
        if (isSuccessful && (this.body() != null)) {
            Result.success(body() as T)
        } else {
            Result.failure(code().toException(message()))
        }
    } catch (e: Throwable) {
        Result.failure(e)
    }
}

