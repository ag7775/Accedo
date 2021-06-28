package com.shivam.kotlin.colormemory_accedo.adapter

import androidx.recyclerview.widget.RecyclerView
import com.shivam.kotlin.colormemory_accedo.data.Score
import com.shivam.kotlin.colormemory_accedo.databinding.ItemScoreBinding


//ViewHolder for RecyclerView
class ScoreViewHolder(
        private val binding: ItemScoreBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(score: Score) {
        binding.apply {
            binding.score = score
            binding.playerRank.text = (adapterPosition + 1).toString()
        }
    }
}