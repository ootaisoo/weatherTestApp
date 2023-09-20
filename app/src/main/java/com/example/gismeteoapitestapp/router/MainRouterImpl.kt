package com.example.gismeteoapitestapp.router

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.gismeteoapitestapp.R
import com.example.gismeteoapitestapp.view.ErrorsLogFragment
import com.example.gismeteoapitestapp.view.ErrorsLogFragment.Companion.MESSAGE
import com.example.gismeteoapitestapp.view.ErrorsLogFragment.Companion.STACK_TRACE
import com.example.gismeteoapitestapp.view.HomeFragment
import com.example.gismeteoapitestapp.view.RequestsHistoryFragment
import java.lang.ref.WeakReference


class MainRouterImpl : MainRouter {

    private lateinit var activityReference: WeakReference<FragmentActivity>
    private lateinit var fragmentReference: WeakReference<Fragment>

    override fun onAttach(activity: FragmentActivity) {
        activityReference = WeakReference(activity)
    }

    override fun onAttachFragment(fragment: Fragment) {
        fragmentReference = WeakReference(fragment)
    }

    override fun showHome() {
        activityReference.get()?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, HomeFragment.newInstance())
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun showRequestsHistory() {
        activityReference.get()?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, RequestsHistoryFragment.newInstance())
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun showErrorsLog(t: Throwable) {
        val bundle = Bundle().apply {
            putString(STACK_TRACE, t.stackTraceToString())
            putString(MESSAGE, t.message)
        }

        fragmentReference.get()?.parentFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, ErrorsLogFragment.newInstance(bundle))
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun sendEmail(text: String) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_TEXT, text)
        val mailer = Intent.createChooser(intent, null)
        fragmentReference.get()?.requireActivity()?.startActivity(mailer)
    }
}