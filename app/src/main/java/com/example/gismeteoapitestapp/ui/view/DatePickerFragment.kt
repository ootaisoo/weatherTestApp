package com.example.gismeteoapitestapp.ui.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.gismeteoapitestapp.ui.viewmodel.HomeViewModel
import java.util.Calendar

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private val homeViewModel: HomeViewModel by viewModels({ requireParentFragment() })

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = homeViewModel.pickedDate.value
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireContext(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        homeViewModel.pickDate(year, month, day)
    }
}