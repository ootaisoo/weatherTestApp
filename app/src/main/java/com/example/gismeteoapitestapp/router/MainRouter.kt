package com.example.gismeteoapitestapp.router

import androidx.fragment.app.FragmentActivity

interface MainRouter {
    fun onAttach(activity: FragmentActivity)
    fun showHome()
    fun showRequestsHistory()
    fun showErrorsLog()
}