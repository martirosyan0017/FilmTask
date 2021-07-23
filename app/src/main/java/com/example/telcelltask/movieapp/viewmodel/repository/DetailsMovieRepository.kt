package com.example.telcelltask.movieapp.viewmodel.repository

import androidx.lifecycle.LiveData
import com.example.telcelltask.movieapp.model.MovieDetails
import com.example.telcelltask.movieapp.webservice.ApiService
import com.example.telcelltask.movieapp.webservice.NetworkState
import io.reactivex.disposables.CompositeDisposable

class DetailsMovieRepository (private val apiService : ApiService) {

    lateinit var movieDetailsNetworkDataSource: DetailsNetworkDataSource

    fun fetchSingleMovieDetails (compositeDisposable: CompositeDisposable, movieId: Int) : LiveData<MovieDetails> {
        movieDetailsNetworkDataSource = DetailsNetworkDataSource(apiService,compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadedMovieResponseLiveData

    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkStateLiveData
    }
}
