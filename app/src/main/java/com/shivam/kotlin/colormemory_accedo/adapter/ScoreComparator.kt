package com.shivam.kotlin.colormemory_accedo.adapter

import androidx.recyclerview.widget.DiffUtil
import com.shivam.kotlin.colormemory_accedo.data.Score


//Comparator for RecyclerView
class ScoreComparator : DiffUtil.ItemCallback<Score>() {
    override fun areItemsTheSame(oldItem: Score, newItem: Score) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Score, newItem: Score) =
       oldItem == newItem
}