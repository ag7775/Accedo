package com.shivam.kotlin.colormemory_accedo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shivam.kotlin.colormemory_accedo.HighScoreRepository
import com.shivam.kotlin.colormemory_accedo.data.Score
import com.shivam.kotlin.colormemory_accedo.widgets.FlipImageView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class GameViewModel @Inject constructor(
        private val repository: HighScoreRepository
) : ViewModel() {

    private val _currentGameScore = MutableLiveData(0)
    val currentGameScore : LiveData<Int>
        get() = _currentGameScore

//    private val _isScoreIncreased = MutableLiveData(false)
//    val isScoreIncreased : LiveData<Boolean>
//        get() = _isScoreIncreased

    private var lastView : FlipImageView? = null
    private val animating = MutableLiveData(false)

    private val _cardCount = MutableLiveData(2 * 2)
    val cardCount : LiveData<Int>
        get() = _cardCount

    @Synchronized
    fun handleClick(view: FlipImageView){
        if (animating.value == true || lastView == view) {
            return
        }

        view.isClickable = false
        animating.value = true
        view.rotationY

        view.reveal{
            if (lastView != null) {
                if (view.puzzleId == lastView?.puzzleId) {
                    animating.value = false
                    _currentGameScore.value = _currentGameScore.value?.plus(2)
                    view.removeSelf()
                    lastView?.removeSelf()
                    lastView = null
                    _cardCount.value = _cardCount.value?.minus(2)
                } else {
                    _currentGameScore.value = _currentGameScore.value?.minus(1)
                    view.hide(lastView!!) {
                        animating.value = false
                        view.isClickable = true
                        lastView!!.isClickable = true
                        lastView = null
                    }
                }
            } else {
                animating.value = false
                lastView = view
            }
        }
    }

    fun saveScore(name:String){
        viewModelScope.launch {
            repository.saveScore(Score(id = Random(100).nextInt(),scores = currentGameScore.value?:0,playerName = name))
        }
    }
}