package com.example.gismeteoapitestapp.view

import android.content.Context
import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.gismeteoapitestapp.R
import com.example.gismeteoapitestapp.component
import com.example.gismeteoapitestapp.databinding.FragmentMainBinding
import com.example.gismeteoapitestapp.model.ClientError
import com.example.gismeteoapitestapp.model.ForecastState
import com.example.gismeteoapitestapp.model.InvalidDateError
import com.example.gismeteoapitestapp.model.ServerError
import com.example.gismeteoapitestapp.model.toMessage
import com.example.gismeteoapitestapp.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    companion object {
        fun newInstance(bundle: Bundle? = null): HomeFragment {
            return HomeFragment().apply {
                arguments = bundle
            }
        }
    }

    private val homeViewModel: HomeViewModel by viewModels {
        requireContext().component.viewModelsFactory()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeViewModel.onAttachFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setInitialDateTime()

        binding.pickDateBtn.setOnClickListener {
//            TODO() make adecuate routing
            DatePickerFragment().show(childFragmentManager, "timePicker")
        }
        binding.requestBtn.setOnClickListener {
            homeViewModel.requestWeather(binding.mainLocationEt.text.toString())
            it.hideKeyboard()
        }
        binding.copyBtn.setOnClickListener {
            homeViewModel.copyToClipboard(binding.forecastTv.text.toString())
        }
        binding.saveBtn.setOnClickListener {
            homeViewModel.saveToDisk(binding.forecastTv.text.toString())
        }

        subscribe()
    }

    private fun setInitialDateTime() {
        binding.datePickedTv.text = homeViewModel.pickedDate.value.formatDate()
    }

    private fun Long.formatDate(): String {
        return DateUtils.formatDateTime(
            context,
            this,
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR)
    }

    private fun subscribe() {
        homeViewModel.apply {
            pickedDate.collectWhenUIVisible(viewLifecycleOwner) { date ->
                binding.datePickedTv.text = date.formatDate()
            }
            forecastState.collectWhenUIVisible(viewLifecycleOwner) { state ->
                handleForecastState(state)
            }
        }
    }

    private fun handleForecastState(state: ForecastState) {
        when (state) {
            is ForecastState.Empty -> {
                binding.forecastLayout.isVisible = false
                binding.progressBar.isVisible = false
            }
            is ForecastState.Loading -> {
                binding.progressBar.isVisible = true
            }
            is ForecastState.Error -> {
                binding.forecastLayout.isVisible = false
                binding.progressBar.isVisible = false
                Snackbar
                    .make(binding.root, state.toMessage(), Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.show_log) {
                        homeViewModel.showLog(state.t)
                    }
                    .show()
            }
            is ForecastState.Success -> {
                binding.forecastLayout.isVisible = true
                binding.progressBar.isVisible = false
                binding.forecastTv.text = state.forecast.toString()
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

fun View.hideKeyboard(): Boolean {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    return inputMethodManager?.hideSoftInputFromWindow(windowToken, 0) == true
}