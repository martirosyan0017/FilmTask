package com.example.telcelltask.movieapp.viewmodel.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.telcelltask.movieapp.appconstants.StatusNetwork
import com.example.telcelltask.movieapp.model.MovieDetails
import com.example.telcelltask.movieapp.webservice.ApiService
import com.example.telcelltask.movieapp.webservice.NetworkState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailsNetworkDataSource(
    private val apiService: ApiService,
    private val compositeDisposable: CompositeDisposable
) {

    private val networkStateMutableLiveData = MutableLiveData<NetworkState>()
    val networkStateLiveData: LiveData<NetworkState>
        get() = networkStateMutableLiveData

    private val detailsResponseMutableLiveData = MutableLiveData<MovieDetails>()
    val downloadedMovieResponseLiveData: LiveData<MovieDetails>
        get() = detailsResponseMutableLiveData

    fun fetchMovieDetails(movieId: Int) {
        networkStateMutableLiveData.postValue(NetworkState.LOADING)
        try {
            compositeDisposable.add(
                apiService.getMovieDetails(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        detailsResponseMutableLiveData.postValue(it)
                        networkStateMutableLiveData.postValue(NetworkState.LOADED)
                    },
                        {
                            networkStateMutableLiveData.postValue(
                                NetworkState(StatusNetwork.FAILED, "Something went wrong")
                            )
                        }
                    )
            )

        } catch (e: Exception) {
            Log.e("MovieDetailsDataSource", e.message!!)
        }
    }
}