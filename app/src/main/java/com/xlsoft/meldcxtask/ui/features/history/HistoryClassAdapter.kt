package com.xlsoft.meldcxtask.ui.features.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xlsoft.meldcxtask.R
import com.xlsoft.meldcxtask.data.models.history.SearchHistory

class HistoryClassAdapter(var historyList: List<SearchHistory>) : RecyclerView.Adapter<HistoryViewHolder>(){

    private lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.view_history_item,
            parent,
            false
        )

        context = parent.context

        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val history = historyList[position]
        holder.urlText.text = history.url
        holder.dateTimeText.text = history.time
        holder.deleteButton.setOnClickListener {
            //todo delete item
        }
    }

    override fun getItemCount() = historyList.size
}

class HistoryViewHolder(view : View) : RecyclerView.ViewHolder(view){
    var screenshotImage = view.findViewById<ImageView>(R.id.screenShot)
    var urlText = view.findViewById<TextView>(R.id.urlText)
    var dateTimeText = view.findViewById<TextView>(R.id.dateTimeText)
    var deleteButton = view.findViewById<ImageView>(R.id.deleteIcon)
}