package com.shivam.kotlin.colormemory_accedo.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//Document Object Model for ROOM DATABASE
@Dao
interface ScoresDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScore(score: Score)

    @Query("SELECT * FROM scores_table")
    fun getAllScores(): LiveData<List<Score>>

    @Query("SELECT COUNT(*) FROM scores_table")
    fun getCount(): Int

}