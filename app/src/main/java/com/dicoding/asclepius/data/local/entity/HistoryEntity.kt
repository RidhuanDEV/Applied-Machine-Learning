package com.dicoding.asclepius.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "History")
@Parcelize
data class  HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "label")
    var label: String = "",
    @ColumnInfo(name = "score")
    var score: Float = 0.0f,
    @ColumnInfo(name = "imageUri")
    var imageUri: String? = null,
) : Parcelable