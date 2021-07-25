package com.example.telcelltask.movieapp.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.telcelltask.movieapp.model.MoviePopular

class MovieDiffCallback : DiffUtil.ItemCallback<MoviePopular>() {
    override fun areItemsTheSame(oldItem: MoviePopular, newItem: MoviePopular): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MoviePopular, newItem: MoviePopular): Boolean {
        return oldItem == newItem
    }
}