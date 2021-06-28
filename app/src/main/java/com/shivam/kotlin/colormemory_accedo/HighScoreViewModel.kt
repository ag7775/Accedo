package com.shivam.kotlin.colormemory_accedo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shivam.kotlin.colormemory_accedo.data.Score
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HighScoreViewModel @Inject constructor(
    private val repository: HighScoreRepository
) : ViewModel() {

    private val _highScoreList = MutableLiveData<List<Score>>()
    val highScoreList: LiveData<List<Score>>
        get() = _highScoreList

//    init {
//        _highScoreList.value = repository.getAllScores().value
//    }

    fun getAllScores(){
        _highScoreList.value = repository.getAllScores().value
    }
}