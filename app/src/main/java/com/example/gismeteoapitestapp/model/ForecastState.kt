package com.example.gismeteoapitestapp.model

import com.example.gismeteoapitestapp.R

sealed class ForecastState {
    object Empty : ForecastState()
    object Loading : ForecastState()
    class Success(val forecast: ForecastResponse) : ForecastState()
    class Error(val t: Throwable) : ForecastState()
}

fun ForecastState.Error.toMessage(): Int {
    return when (t) {
        is ClientError -> R.string.client_error
        is ServerError -> R.string.server_error
        is InvalidDateError -> R.string.invalid_date_error
        else -> R.string.unknown_error
    }
}