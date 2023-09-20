package com.example.gismeteoapitestapp.viewmodel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.gismeteoapitestapp.router.MainRouter
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private var mainRouter: MainRouter,
) : ViewModel() {

    fun onAttach(activity: FragmentActivity) = mainRouter.onAttach(activity)
    fun showHome() = mainRouter.showHome()
    fun showRequestsHistory() = mainRouter.showRequestsHistory()
}