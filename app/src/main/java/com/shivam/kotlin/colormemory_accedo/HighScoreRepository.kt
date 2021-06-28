package com.shivam.kotlin.colormemory_accedo

import androidx.lifecycle.LiveData
import com.shivam.kotlin.colormemory_accedo.data.AppDatabase
import com.shivam.kotlin.colormemory_accedo.data.Score
import javax.inject.Inject

class HighScoreRepository @Inject constructor(
        private val db: AppDatabase
) {

    private val scoresDao = db.getScoresDao()

    fun getAllScores(): LiveData<List<Score>> {
        return scoresDao.getAllScores()
    }

    suspend fun saveScore(score:Score){
        scoresDao.insertScore(score)
    }
}