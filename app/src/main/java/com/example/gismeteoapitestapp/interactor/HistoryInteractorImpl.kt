package com.example.gismeteoapitestapp.interactor

import com.example.gismeteoapitestapp.repository.CachingRepository

class HistoryInteractorImpl(
    private val cachingRepository: CachingRepository
) : HistoryInteractor {

    override fun fetchHistory(): String {
        return cachingRepository.fetchRequestsInfo()
    }
}