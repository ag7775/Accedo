package com.shivam.kotlin.colormemory_accedo

import androidx.lifecycle.LiveData
import com.shivam.kotlin.colormemory_accedo.data.Score
import com.shivam.kotlin.colormemory_accedo.data.ScoresDao
import javax.inject.Inject

class HighScoreRepository @Inject constructor(
    private val scoresDao: ScoresDao
) {

    fun getAllScores(): LiveData<List<Score>> {
        return scoresDao.getAllScores()
    }

    suspend fun saveScore(score:Score){
        scoresDao.insertScore(score)
    }
}