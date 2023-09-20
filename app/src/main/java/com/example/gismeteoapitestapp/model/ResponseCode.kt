package com.example.gismeteoapitestapp.model

import java.io.IOException

object ResponseCode {
    const val INTERNAL_SERVER_ERROR = 500
    private const val CLIENT_ERROR_CODE_RANGE_ST = 400
    private const val SERVER_ERROR_CODE_RANGE_EN = 599

    val CLIENT_ERROR_RESPONSES = CLIENT_ERROR_CODE_RANGE_ST until INTERNAL_SERVER_ERROR
    val SERVER_ERROR_RESPONSES = INTERNAL_SERVER_ERROR..SERVER_ERROR_CODE_RANGE_EN

    fun Int.toException(message: String?): Throwable {
        return when (this) {
            in CLIENT_ERROR_RESPONSES -> ClientError(message)
            in SERVER_ERROR_RESPONSES -> ServerError(message)
            else -> UnknownError()
        }
    }

    class ClientError(
        override val message: String? = null
    ) : IOException(message)

    class ServerError(
        override val message: String? = null
    ) : IOException(message)

    class UnknownError(
        message: String? = null
    ) : IOException(message)
}