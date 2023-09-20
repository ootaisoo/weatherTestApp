package com.example.gismeteoapitestapp.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.gismeteoapitestapp.interactor.CachingInteractor
import com.example.gismeteoapitestapp.router.MainRouter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ErrorsLogViewModel @Inject constructor(
    private var router: MainRouter,
) : ViewModel() {

    fun onAttachFragment(fragment: Fragment) = router.onAttachFragment(fragment)
    fun sendEmail(text: String) = router.sendEmail(text)
}