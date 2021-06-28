package com.shivam.kotlin.colormemory_accedo.ui.screens

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.shivam.kotlin.colormemory_accedo.HighScoreViewModel
import com.shivam.kotlin.colormemory_accedo.R
import com.shivam.kotlin.colormemory_accedo.adapter.ScoreAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentHighScore : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val highScoreViewModel : HighScoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_highscores)

        val scoreAdapter = ScoreAdapter()

        recyclerView = findViewById(R.id.highScoreRecyclerView)
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = scoreAdapter
        }

        highScoreViewModel.highScoreList.observe(this){ highScoreList ->
            scoreAdapter.submitList(highScoreList)
        }

        highScoreViewModel.getAllScores()
    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_highscores, container, false)
//    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val scoreAdapter = ScoreAdapter()
//
//        recyclerView = view.findViewById(R.id.highScoreRecyclerView)
//        recyclerView.apply {
//            setHasFixedSize(true)
//            adapter = scoreAdapter
//        }
//
//        highScoreViewModel.highScoreList.observe(viewLifecycleOwner){ highScoreList ->
//            scoreAdapter.submitList(highScoreList)
//        }
//    }
}