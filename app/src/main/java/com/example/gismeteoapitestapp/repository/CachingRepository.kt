package com.example.gismeteoapitestapp.repository

interface CachingRepository {
    fun copyToClipboard(text: String)
    fun saveToDownloads(text: String)
}