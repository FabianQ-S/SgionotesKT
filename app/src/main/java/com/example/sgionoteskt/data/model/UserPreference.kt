package com.example.sgionoteskt.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_preferences")
data class UserPreference(
    @PrimaryKey
    @ColumnInfo(name = "pref_key")
    val key: String,

    @ColumnInfo(name = "pref_value")
    val value: String
)
