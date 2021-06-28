package com.shivam.kotlin.colormemory_accedo.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.text.DateFormat

@Entity(tableName = "scores_table")
data class Score(
        @NotNull
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        @NotNull
        val scores: Int,
        val playerName: String,
        val createdDate: Long = System.currentTimeMillis()
) {
    val startDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(createdDate)
}
