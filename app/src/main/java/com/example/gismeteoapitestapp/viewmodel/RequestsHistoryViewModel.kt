package com.example.gismeteoapitestapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.gismeteoapitestapp.interactor.CachingInteractor
import com.example.gismeteoapitestapp.model.HistoryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class RequestsHistoryViewModel @Inject constructor(
    private val cachingInteractor: CachingInteractor
) : ViewModel() {

    private val _historyItems: MutableStateFlow<List<HistoryItem>> = MutableStateFlow(mutableListOf())
    val historyItems: StateFlow<List<HistoryItem>> = _historyItems.asStateFlow()

    fun fetchHistory() {
        _historyItems.value = cachingInteractor.fetchHistory().parseHistory()
    }

    fun String.parseHistory(): List<HistoryItem> {
        return mutableListOf()
    }
}