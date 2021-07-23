package com.example.telcelltask.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.telcelltask.movieapp.viewmodel.repository.DetailsMovieRepository
import io.reactivex.disposables.CompositeDisposable

class DetailsMovieViewModel(private val detailsRepository: DetailsMovieRepository, movieId: Int) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val movieDetails by lazy {
        detailsRepository.fetchSingleMovieDetails(compositeDisposable, movieId)
    }

    val networkState by lazy {
        detailsRepository.getMovieDetailsNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}