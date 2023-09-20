package com.example.gismeteoapitestapp.router

import androidx.fragment.app.FragmentActivity
import com.example.gismeteoapitestapp.R
import com.example.gismeteoapitestapp.view.RequestsHistoryFragment
import com.example.gismeteoapitestapp.view.LogFragment
import com.example.gismeteoapitestapp.view.MainFragment
import java.lang.ref.WeakReference

class MainRouterImpl : MainRouter {

    private lateinit var activityReference: WeakReference<FragmentActivity>

    override fun onAttach(activity: FragmentActivity) {
        activityReference = WeakReference(activity)
    }

    override fun showHome() {
        activityReference.get()?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, MainFragment())
            ?.commit()
    }

    override fun showRequestsHistory() {
        activityReference.get()?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, RequestsHistoryFragment())
            ?.commit()
    }

    override fun showErrorsLog() {
        activityReference.get()?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, LogFragment())
            ?.commit()
    }
}