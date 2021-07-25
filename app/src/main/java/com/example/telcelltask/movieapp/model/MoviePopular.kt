package com.example.telcelltask.movieapp.model

import com.google.gson.annotations.SerializedName

data class MoviePopular(
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String,
    val adult: Boolean
)