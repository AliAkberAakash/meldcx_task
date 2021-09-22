package com.xlsoft.meldcxtask.ui.features.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xlsoft.meldcxtask.R
import com.xlsoft.meldcxtask.data.models.history.SearchHistory
import java.io.File

class HistoryListAdapter(
    var historyList: List<SearchHistory>,
    val deleteCallBack : (history : SearchHistory)-> Unit,
    val itemClickCallBack : (history : SearchHistory)-> Unit
) : RecyclerView.Adapter<HistoryViewHolder>(){

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

        // Set values to fields
        val history = historyList[position]
        holder.urlText.text = history.url
        holder.dateTimeText.text = history.created_at

        // Load image from file
        Glide
            .with(context)
            .load(File(history.imagePath))
            .centerCrop()
            //.placeholder(R.drawable.loading_spinner)
            .into(holder.screenshotImage)

        // delete item
        holder.deleteButton.setOnClickListener {
            deleteCallBack(historyList[position])
        }

        // itemCLick CallBack
        holder.screenshotImage.setOnClickListener {
             itemClickCallBack(historyList[position])
        }
        holder.urlText.setOnClickListener {
            itemClickCallBack(historyList[position])
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