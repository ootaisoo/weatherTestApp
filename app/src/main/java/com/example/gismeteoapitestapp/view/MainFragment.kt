package com.example.gismeteoapitestapp.view

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.gismeteoapitestapp.R
import com.example.gismeteoapitestapp.component
import com.example.gismeteoapitestapp.model.ForecastState
import com.example.gismeteoapitestapp.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


class MainFragment : Fragment() {

    private lateinit var root: ViewGroup
    private lateinit var pickedDateTV: TextView
    private lateinit var pickDateBtn: Button
    private lateinit var requestBtn: Button
    private lateinit var locationET: EditText
    private lateinit var forecastTV: TextView
    private lateinit var forecastLayout: ViewGroup
    private lateinit var progressBar: ProgressBar

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

        root = view.findViewById(R.id.root)
        pickedDateTV = view.findViewById(R.id.date_picked_tv)
        pickDateBtn = view.findViewById(R.id.pick_date_btn)
        requestBtn = view.findViewById(R.id.request_btn)
        locationET = view.findViewById(R.id.main_location_et)
        forecastTV = view.findViewById(R.id.forecast_tv)
        forecastLayout = view.findViewById(R.id.forecast_layout)
        progressBar = view.findViewById(R.id.progress_bar)

        setInitialDateTime()

        pickDateBtn.setOnClickListener {
//            TODO() make adecuate routing
            DatePickerFragment().show(childFragmentManager, "timePicker")
        }
        requestBtn.setOnClickListener {
            mainViewModel.requestWeather(locationET.text.toString())
        }

        subscribe()
    }

    private fun setInitialDateTime() {
        pickedDateTV.text = mainViewModel.pickedDate.value.formatDate()
    }

    private fun Long.formatDate(): String {
        return DateUtils.formatDateTime(
            context,
            this,
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR)
    }

    private fun subscribe() {
        mainViewModel.apply {
            pickedDate.collectWhenUIVisible(viewLifecycleOwner) { date ->
                pickedDateTV.text = date.formatDate()
            }
            forecastState.collectWhenUIVisible(viewLifecycleOwner) { state ->
                handleForecastState(state)
            }
        }
    }

    private fun handleForecastState(state: ForecastState) {
        when (state) {
            is ForecastState.Empty -> {
                forecastLayout.isVisible = false
                progressBar.isVisible = false
            }
            is ForecastState.Loading -> {
                progressBar.isVisible = true
            }
            is ForecastState.Error -> {
                forecastLayout.isVisible = false
                progressBar.isVisible = false
                Snackbar.make(root, R.string.error, Snackbar.LENGTH_INDEFINITE).show()
            }
            is ForecastState.Success -> {
                forecastLayout.isVisible = true
                progressBar.isVisible = false
                forecastTV.text = state.forecast.description.full
            }
        }
    }

}

fun <T> Flow<T>.collectWhenUIVisible(
    owner: LifecycleOwner,
    context: CoroutineContext = EmptyCoroutineContext,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    block: (suspend (T) -> Unit)? = null,
) = owner.lifecycleScope.launch(
    context = context,
    block = {
        owner.repeatOnLifecycle(lifecycleState) {
            if (block != null) {
                collect(block)
            } else {
                collect()
            }
        }
    },
)