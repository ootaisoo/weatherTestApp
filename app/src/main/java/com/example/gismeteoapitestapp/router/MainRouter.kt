package com.example.gismeteoapitestapp.router

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

interface MainRouter {
    fun onAttach(activity: FragmentActivity)
    fun onAttachFragment(fragment: Fragment)
    fun showHome()
    fun showRequestsHistory()
    fun showErrorsLog(t: Throwable)
    fun sendEmail(text: String)
}