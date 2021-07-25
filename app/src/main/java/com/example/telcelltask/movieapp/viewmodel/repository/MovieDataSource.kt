package com.example.telcelltask.movieapp.viewmodel.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.telcelltask.movieapp.appconstants.DBClient.FIRST_PAGE
import com.example.telcelltask.movieapp.appconstants.StatusNetwork
import com.example.telcelltask.movieapp.model.MoviePopular
import com.example.telcelltask.movieapp.webservice.ApiService
import com.example.telcelltask.movieapp.webservice.NetworkState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDataSource(
    private val apiService: ApiService,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, MoviePopular>() {

    val networkState: MutableLiveData<NetworkState> = MutableLiveData()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MoviePopular>
    ) {

        networkState.postValue(NetworkState.LOADING)

        compositeDisposable.add(
            apiService.getMovie(FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    callback.onResult(it.movieList, null, FIRST_PAGE + 1)
                    networkState.postValue(NetworkState.LOADED)
                },
                    {
                        networkState.postValue(
                            NetworkState(
                                StatusNetwork.FAILED,
                                "No network connection"
                            )
                        )
                    }
                )
        )

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MoviePopular>) {

        networkState.postValue(NetworkState.LOADING)

        compositeDisposable.add(
            apiService.getMovie(params.key)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    if (it.totalPages >= params.key) {
                        callback.onResult(it.movieList, params.key + 1)
                        networkState.postValue(NetworkState.LOADED)
                    } else {
                        networkState.postValue(
                            NetworkState(
                                StatusNetwork.FAILED,
                                "You have reached the end"
                            )
                        )
                    }
                }, {
                    networkState.postValue(
                        NetworkState(
                            StatusNetwork.FAILED,
                            "No network connection"
                        )
                    )
                }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MoviePopular>) {

    }
}