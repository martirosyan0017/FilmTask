package com.example.telcelltask.movieapp.viewmodel.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.telcelltask.movieapp.model.MoviePoster
import com.example.telcelltask.movieapp.webservice.ApiService
import io.reactivex.disposables.CompositeDisposable

class DataSourceFactory (private val apiService : ApiService, private val compositeDisposable: CompositeDisposable) : DataSource.Factory<Int, MoviePoster>() {

    val moviesLiveDataSource = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, MoviePoster> {
        val movieDataSource = MovieDataSource(apiService, compositeDisposable)

        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}