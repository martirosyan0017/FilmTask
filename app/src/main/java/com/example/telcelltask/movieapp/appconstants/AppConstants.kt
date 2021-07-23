package com.example.telcelltask.movieapp.appconstants

enum class StatusNetwork {
    RUNNING,
    SUCCESS,
    FAILED
}

object Root {
    const val GET_MOVIE = "movie/popular"
    const val GET_DETAILS = "movie/{movie_id}"
    const val QUERY_VALUE = "page"
    const val PATH_VALUE = "movie_id"
}

object DBClient{
    const val API_KEY = "457ea5d2e1ba7e87eb72ee370b4cac56"
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY_PARAMETER = "api_key"
    const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"
    const val FIRST_PAGE = 1
    const val POST_PER_PAGE = 20
}



