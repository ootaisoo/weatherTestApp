package com.example.gismeteoapitestapp.interactor

import com.example.gismeteoapitestapp.repository.CachingRepository

class CachingInteractorImpl(
    private val cachingRepository: CachingRepository
) : CachingInteractor {

    override fun copyToClipboard(text: String) {
        cachingRepository.copyToClipboard(text)
    }

    override fun saveToDisk(text: String) {
        cachingRepository.saveToDownloads(text)
    }
}