package com.example.gismeteoapitestapp.data.repository

interface CachingRepository {
    fun copyToClipboard(text: String)
    fun saveToDownloads(text: String)
    fun saveRequestsInfo(requestsInfo: String)
    fun fetchRequestsInfo(): String
}