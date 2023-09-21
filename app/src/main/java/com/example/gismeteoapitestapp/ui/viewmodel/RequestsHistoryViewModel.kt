package com.example.gismeteoapitestapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.gismeteoapitestapp.interactor.HistoryInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class RequestsHistoryViewModel @Inject constructor(
    private val historyInteractor: HistoryInteractor
) : ViewModel() {

    private val END_HTTP_REGEX = "<-- END HTTP \\(.*\\)".toRegex()

    private val _historyItems: MutableStateFlow<List<String>> = MutableStateFlow(mutableListOf())
    val historyItems: StateFlow<List<String>> = _historyItems.asStateFlow()

    fun fetchHistory() {
        _historyItems.value = historyInteractor.fetchHistory().parseHistory()
    }

    fun String.parseHistory(): List<String> {
        return split(END_HTTP_REGEX).toList()
    }
}