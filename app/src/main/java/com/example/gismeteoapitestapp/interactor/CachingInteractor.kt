package com.example.gismeteoapitestapp.interactor

import com.example.gismeteoapitestapp.model.HistoryItem

interface CachingInteractor {
    fun copyToClipboard(text: String)
    fun saveToDisk(text: String)
    fun fetchHistory(): String
}