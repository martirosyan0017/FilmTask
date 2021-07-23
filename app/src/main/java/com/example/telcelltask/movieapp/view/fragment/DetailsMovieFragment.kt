package com.example.telcelltask.movieapp.view.fragment

import ConnectivityInterceptor
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.telcelltask.R
import com.example.telcelltask.movieapp.appconstants.DBClient.POSTER_BASE_URL
import com.example.telcelltask.movieapp.appconstants.StatusNetwork
import com.example.telcelltask.movieapp.base.BaseFragment
import com.example.telcelltask.movieapp.model.MovieDetails
import com.example.telcelltask.movieapp.viewmodel.DetailsMovieViewModel
import com.example.telcelltask.movieapp.viewmodel.repository.DetailsMovieRepository
import com.example.telcelltask.movieapp.webservice.ApiService
import com.example.telcelltask.movieapp.webservice.NetworkState
import com.example.telcelltask.movieapp.webservice.RetrofitClient
import kotlinx.android.synthetic.main.details_movie_fragment.*

@Suppress("UNCHECKED_CAST")
class DetailsMovieFragment : BaseFragment(R.layout.details_movie_fragment) {

    private lateinit var viewModel: DetailsMovieViewModel
    private lateinit var repository: DetailsMovieRepository


    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                openFragment(R.id.fragment_container, MainMovieFragment())
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val apiService: ApiService = RetrofitClient.getClient(ConnectivityInterceptor(requireActivity()))
        repository = DetailsMovieRepository(apiService)


        /*
            val movieId: Int = intent.getIntExtra("id",1)
        viewModel = getViewModel(movieId)
         */

        val index = arguments?.getInt("1", 1)

        viewModel = getViewModel(index!!)

     //   viewModel = ViewModelProviders.of(this, factory).get(DetailsMovieViewModel::class.java)


        viewModel.movieDetails.observe(viewLifecycleOwner, Observer {
            bindUI(it)
        })

        viewModel.networkState.observe(viewLifecycleOwner, Observer {
            progress_bar.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE

            txt_error.visibility = if (it.statusNetwork == StatusNetwork.FAILED) View.VISIBLE else View.GONE

        })
    }


    private fun initViewModel() {

    }
    private fun bindUI(it: MovieDetails) {
        movie_title.text = it.title
        movie_tagline.text = it.tagline
        movie_release_date.text = it.releaseDate
        movie_rating.text = it.rating
        movie_overview.text = it.overview
        val moviePosterURL = POSTER_BASE_URL + it.posterPath
        Glide.with(this).load(moviePosterURL).into(image_details);
    }

    private fun getViewModel(movieId:Int): DetailsMovieViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return DetailsMovieViewModel(repository,movieId) as T
            }
        })[DetailsMovieViewModel::class.java]
    }

  /*  class ViewModelFactory(private val repository: DetailsMovieRepository, private val movieId: Int) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DetailsMovieViewModel(repository,movieId) as T
        }
    }*/
}