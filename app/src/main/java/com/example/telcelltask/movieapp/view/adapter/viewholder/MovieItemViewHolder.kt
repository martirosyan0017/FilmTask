package com.example.telcelltask.movieapp.view.adapter.viewholder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.telcelltask.movieapp.appconstants.DBClient
import com.example.telcelltask.movieapp.model.MoviePopular
import kotlinx.android.synthetic.main.main_movie_list.view.*

class MovieItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(moviePopular: MoviePopular?, context: Context, onClick: ((MoviePopular) -> Unit?)?) {
        itemView.movie_title.text = moviePopular?.title
        itemView.movie_release_date.text = moviePopular?.releaseDate

        val moviePosterURL = DBClient.POSTER_BASE_URL + moviePopular?.posterPath
        // Image loading library for Android backed by Kotlin Coroutines
        itemView.cv_iv_movie_poster.load(moviePosterURL) {
            crossfade(true)
        }

        itemView.setOnClickListener {
            if (onClick != null)
                onClick(moviePopular!!)
        }
    }
}
