package com.example.telcelltask.movieapp.webservice

import com.example.telcelltask.movieapp.appconstants.Root
import com.example.telcelltask.movieapp.model.MovieDetails
import com.example.telcelltask.movieapp.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(Root.GET_MOVIE)
    fun getMovie(@Query(Root.QUERY_VALUE) page: Int): Single<MovieResponse>

    @GET(Root.GET_DETAILS)
    fun getMovieDetails(@Path(Root.PATH_VALUE) id: Int): Single<MovieDetails>

}