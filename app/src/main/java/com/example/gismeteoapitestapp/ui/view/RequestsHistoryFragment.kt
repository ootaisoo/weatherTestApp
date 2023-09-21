package com.example.gismeteoapitestapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gismeteoapitestapp.component
import com.example.gismeteoapitestapp.databinding.FragmentRequestsHistoryBinding
import com.example.gismeteoapitestapp.ui.viewmodel.RequestsHistoryViewModel

class RequestsHistoryFragment : Fragment() {

    companion object {
        fun newInstance(bundle: Bundle? = null): RequestsHistoryFragment {
            return RequestsHistoryFragment().apply {
                arguments = bundle
            }
        }
    }

    private lateinit var binding: FragmentRequestsHistoryBinding

    private lateinit var requestsAdapter: RequestsAdapter

    private val viewModel: RequestsHistoryViewModel by viewModels {
        requireContext().component.viewModelsFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        subscribe()
        binding = FragmentRequestsHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestsAdapter = RequestsAdapter()
        binding.requestsRecycler.adapter = requestsAdapter

        viewModel.fetchHistory()
    }

    private fun subscribe() {
        viewModel.historyItems.collectWhenUIVisible(viewLifecycleOwner) {
            requestsAdapter.update(it)
        }
    }
}