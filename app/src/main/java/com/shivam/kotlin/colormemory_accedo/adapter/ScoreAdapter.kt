package com.shivam.kotlin.colormemory_accedo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.shivam.kotlin.colormemory_accedo.data.Score
import com.shivam.kotlin.colormemory_accedo.databinding.ItemScoreBinding

class ScoreAdapter : ListAdapter<Score, ScoreViewHolder>(ScoreComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        return ScoreViewHolder(
                ItemScoreBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(getItem(position))
        }
    }
}