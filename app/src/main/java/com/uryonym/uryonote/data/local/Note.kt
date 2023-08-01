package com.uryonym.uryonote.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(
    @PrimaryKey val id: String,
    var title: String,
    var content: String
)
