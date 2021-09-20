package com.xlsoft.meldcxtask.data.models.history

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search-history")
data class SearchHistory(
    @PrimaryKey
    var id : Long,
    var url : String,
    var imagePath : String,
    var time : String,
)
