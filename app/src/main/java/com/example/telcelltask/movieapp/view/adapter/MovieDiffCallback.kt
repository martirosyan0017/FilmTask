package com.example.telcelltask.movieapp.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.telcelltask.movieapp.model.MoviePoster

class MovieDiffCallback : DiffUtil.ItemCallback<MoviePoster>() {
    override fun areItemsTheSame(oldItem: MoviePoster, newItem: MoviePoster): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MoviePoster, newItem: MoviePoster): Boolean {
        return oldItem == newItem
    }
}