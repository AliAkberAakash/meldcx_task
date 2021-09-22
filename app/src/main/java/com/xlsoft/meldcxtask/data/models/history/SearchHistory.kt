package com.xlsoft.meldcxtask.data.models.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SearchHistory")
data class SearchHistory(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id")
    var id : Int? = null,
    @ColumnInfo(name ="url")
    var url : String,
    @ColumnInfo(name ="image_path")
    var imagePath : String,
    @ColumnInfo(name ="created_at")
    var created_at : String,
)