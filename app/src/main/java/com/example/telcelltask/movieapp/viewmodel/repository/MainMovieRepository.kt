package com.example.telcelltask.movieapp.viewmodel.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.telcelltask.movieapp.appconstants.DBClient.POST_PER_PAGE
import com.example.telcelltask.movieapp.model.MoviePoster
import com.example.telcelltask.movieapp.webservice.ApiService
import com.example.telcelltask.movieapp.webservice.NetworkState
import io.reactivex.disposables.CompositeDisposable

class MainMovieRepository(private val apiService: ApiService) {

    private lateinit var moviePagedList: LiveData<PagedList<MoviePoster>>
    private lateinit var moviesDataSourceFactory: DataSourceFactory

    fun fetchLiveMoviePagedList(compositeDisposable: CompositeDisposable): LiveData<PagedList<MoviePoster>> {
        moviesDataSourceFactory = DataSourceFactory(apiService, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        val filteredData = moviesDataSourceFactory;



        moviePagedList = LivePagedListBuilder(filteredData, config).build()

        return moviePagedList

    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap(moviesDataSourceFactory.moviesLiveDataSource, MovieDataSource::networkState)
    }
}