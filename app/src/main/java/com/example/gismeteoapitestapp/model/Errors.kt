package com.example.gismeteoapitestapp.model

import java.io.IOException

class ClientError(
    override val message: String? = null
) : IOException(message)

class ServerError(
    override val message: String? = null
) : IOException(message)

class UnknownError(
    message: String? = null
) : IOException(message)

class InvalidDateError(
    message: String? = null
) : Exception(message)