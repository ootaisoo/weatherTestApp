package com.example.gismeteoapitestapp.ui.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.gismeteoapitestapp.ui.router.MainRouter
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private var mainRouter: MainRouter,
) : ViewModel() {

    fun onAttach(activity: FragmentActivity) = mainRouter.onAttach(activity)
    fun showHome() = mainRouter.showHome()
    fun showRequestsHistory() = mainRouter.showRequestsHistory()
}