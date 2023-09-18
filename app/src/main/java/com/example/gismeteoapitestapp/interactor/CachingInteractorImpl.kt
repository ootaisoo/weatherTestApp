package com.example.gismeteoapitestapp.interactor

import com.example.gismeteoapitestapp.repository.CachingRepository

class CachingInteractorImpl(
    private val cachingRepository: CachingRepository
) : CachingInteractor {

    override fun copyTextToClipboard(text: String) {
        cachingRepository.copyTextToClipboard(text)
    }

    override fun saveToExternalStorage(text: String) {
        cachingRepository.saveToExternalStorage(text)
    }
}