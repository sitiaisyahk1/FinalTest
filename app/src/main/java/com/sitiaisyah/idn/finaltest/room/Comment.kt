package com.sitiaisyah.idn.finaltest.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment_table")
data class Comment(
    @PrimaryKey @ColumnInfo(name = "comment")
    val comment: String
)