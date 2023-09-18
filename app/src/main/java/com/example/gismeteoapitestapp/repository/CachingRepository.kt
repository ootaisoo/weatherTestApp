package com.example.gismeteoapitestapp.repository

interface CachingRepository {
    fun copyTextToClipboard(text: String)
    fun saveToExternalStorage(text: String)
}