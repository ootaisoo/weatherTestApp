package com.example.gismeteoapitestapp.interactor

interface CachingInteractor {
    fun copyToClipboard(text: String)
    fun saveToDisk(text: String)
    fun fetchHistory(): String
}