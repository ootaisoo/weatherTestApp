package com.example.gismeteoapitestapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gismeteoapitestapp.R
import com.example.gismeteoapitestapp.model.HistoryItem

class RequestsAdapter : RecyclerView.Adapter<HistoryViewHolder>() {

    private val items: MutableList<HistoryItem> = mutableListOf()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_holder, parent, false)

        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun update(newItems: List<HistoryItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}

class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: HistoryItem) {
        itemView.findViewById<TextView>(R.id.request_tv).text = item.request
        itemView.findViewById<TextView>(R.id.response_tv).text = item.response
    }
}