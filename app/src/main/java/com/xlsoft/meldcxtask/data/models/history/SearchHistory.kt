package com.xlsoft.meldcxtask.data.models.history

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SearchHistory")
data class SearchHistory(
    @PrimaryKey(autoGenerate = true)
    var id : Long,
    var url : String,
    var imagePath : String,
    var time : String,
)
