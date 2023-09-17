package com.example.gismeteoapitestapp.view

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.gismeteoapitestapp.R
import com.example.gismeteoapitestapp.component
import com.example.gismeteoapitestapp.viewmodel.MainViewModel
import kotlinx.coroutines.launch


class MainFragment : Fragment() {

    private lateinit var pickedDate: TextView
    private lateinit var pickDateBtn: Button
    private lateinit var requestBtn: Button

    private val mainViewModel: MainViewModel by viewModels {
        requireContext().component.viewModelsFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pickedDate = view.findViewById(R.id.date_picked_tv)
        pickDateBtn = view.findViewById(R.id.pick_date_btn)
        requestBtn = view.findViewById(R.id.request_btn)

        setInitialDateTime()

        pickDateBtn.setOnClickListener {
//            TODO() make adecuate routing
            DatePickerFragment().show(childFragmentManager, "timePicker")
        }
        requestBtn.setOnClickListener {
            mainViewModel.requestWeather()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.pickedDate.collect {
                    pickedDate.text = it.formatDate()
                }
            }
        }

    }

    private fun setInitialDateTime() {
        pickedDate.text = mainViewModel.pickedDate.value.formatDate()
    }

    private fun Long.formatDate(): String {
        return DateUtils.formatDateTime(
            context,
            this,
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR)
    }
}