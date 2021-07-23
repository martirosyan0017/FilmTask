package com.example.telcelltask.movieapp.view.adapter.viewholder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.telcelltask.movieapp.appconstants.DBClient
import com.example.telcelltask.movieapp.model.MoviePoster
import kotlinx.android.synthetic.main.main_movie_list.view.*

class MovieItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(moviePoster: MoviePoster?, context: Context, onClick: ((MoviePoster) -> Unit?)?){
        itemView.movie_title.text = moviePoster?.title
        itemView.movie_release_date.text = moviePoster?.releaseDate
        val moviePosterURL = DBClient.POSTER_BASE_URL + moviePoster?.posterPath
        Glide.with(itemView.context).load(moviePosterURL).into(itemView.cv_iv_movie_poster)

        itemView.setOnClickListener {
            if (onClick != null)
                onClick(moviePoster!!)
        }

    }
}
