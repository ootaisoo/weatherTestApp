package com.example.gismeteoapitestapp.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.gismeteoapitestapp.router.MainRouter
import javax.inject.Inject

class ErrorsLogViewModel @Inject constructor(
    private var router: MainRouter,
) : ViewModel() {

    fun onAttachFragment(fragment: Fragment) = router.onAttachFragment(fragment)
    fun sendEmail(text: String) = router.sendEmail(text)
}