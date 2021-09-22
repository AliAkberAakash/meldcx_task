package com.xlsoft.meldcxtask.data.models.history

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SearchHistory")
data class SearchHistory(
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,
    var url : String,
    var imagePath : String,
    var time : String,
)