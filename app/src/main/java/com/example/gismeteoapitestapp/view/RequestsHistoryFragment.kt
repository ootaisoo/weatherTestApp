package com.example.gismeteoapitestapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gismeteoapitestapp.R
import com.example.gismeteoapitestapp.component
import com.example.gismeteoapitestapp.viewmodel.HomeViewModel
import com.example.gismeteoapitestapp.viewmodel.RequestsHistoryViewModel

class RequestsHistoryFragment : Fragment() {

    private lateinit var recycler: RecyclerView
    private lateinit var requestsAdapter: RequestsAdapter

    private val viewModel: RequestsHistoryViewModel by viewModels {
        requireContext().component.viewModelsFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        subscribe()
        return inflater.inflate(R.layout.fragment_requests_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById(R.id.requests_recycler)
        requestsAdapter = RequestsAdapter()
        recycler.adapter = requestsAdapter

        viewModel.fetchHistory()
    }

    private fun subscribe() {
        viewModel.historyItems.collectWhenUIVisible(viewLifecycleOwner) {
            requestsAdapter.update(it)
        }
    }
}