package com.example.gismeteoapitestapp

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.Calendar


class MainFragment : Fragment() {

    private var pickedDateTime: TextView? = null
    private val date = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pickedDateTime = view.findViewById(R.id.date_picked)
        setInitialDateTime()
    }

    private fun setInitialDateTime() {
        pickedDateTime?.text = DateUtils.formatDateTime(
            context,
            date.timeInMillis,
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR
        )
    }
}