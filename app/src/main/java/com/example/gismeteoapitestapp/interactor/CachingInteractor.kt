package com.example.gismeteoapitestapp.interactor

interface CachingInteractor {
    fun copyTextToClipboard(text: String)
    fun saveToExternalStorage(text: String)
}