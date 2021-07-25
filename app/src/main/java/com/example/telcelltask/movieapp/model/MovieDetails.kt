package com.example.telcelltask.movieapp.model

import com.google.gson.annotations.SerializedName

data class MovieDetails(
    val homepage: String,
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val tagline: String,
    val status: String,
    @SerializedName("vote_average")
    val rating: String,
    val title: String
)