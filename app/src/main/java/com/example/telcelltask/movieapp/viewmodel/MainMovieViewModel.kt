package com.example.telcelltask.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.telcelltask.movieapp.viewmodel.repository.MainMovieRepository
import io.reactivex.disposables.CompositeDisposable


class MainMovieViewModel(private val movieRepository: MainMovieRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val moviePagedList by lazy {
        movieRepository.fetchLiveMoviePagedList(compositeDisposable)
    }

    val networkState by lazy {
        movieRepository.getNetworkState()
    }

    fun listIsEmpty(): Boolean {
        return moviePagedList.value?.isEmpty() ?: true
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
