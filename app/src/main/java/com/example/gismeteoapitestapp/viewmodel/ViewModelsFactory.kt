package com.example.gismeteoapitestapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    mainViewModelProvider: Provider<MainViewModel>,
    homeViewModelProvider: Provider<HomeViewModel>,
    requestsHistoryViewModel: Provider<RequestsHistoryViewModel>,
    errorsLogViewModel: Provider<ErrorsLogViewModel>,
) : ViewModelProvider.Factory {
    private val providers = mapOf<Class<*>, Provider<out ViewModel>>(
        MainViewModel::class.java to mainViewModelProvider,
        HomeViewModel::class.java to homeViewModelProvider,
        RequestsHistoryViewModel::class.java to requestsHistoryViewModel,
        ErrorsLogViewModel::class.java to errorsLogViewModel
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return providers[modelClass]!!.get() as T
    }
}