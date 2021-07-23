package com.example.telcelltask.movieapp.view.fragment

import ConnectivityInterceptor
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.telcelltask.R
import com.example.telcelltask.movieapp.appconstants.StatusNetwork
import com.example.telcelltask.movieapp.base.BaseActivity
import com.example.telcelltask.movieapp.base.BaseFragment
import com.example.telcelltask.movieapp.view.adapter.MoviePageListAdapter
import com.example.telcelltask.movieapp.viewmodel.MainMovieViewModel
import com.example.telcelltask.movieapp.viewmodel.repository.MainMovieRepository
import com.example.telcelltask.movieapp.webservice.ApiService
import com.example.telcelltask.movieapp.webservice.NetworkState
import com.example.telcelltask.movieapp.webservice.RetrofitClient
import kotlinx.android.synthetic.main.main_movie_fragment.*

@Suppress("UNCHECKED_CAST")
class MainMovieFragment : BaseFragment(R.layout.main_movie_fragment) {

    private lateinit var viewModel: MainMovieViewModel
    private lateinit var repository: MainMovieRepository
    private lateinit var apiService: ApiService


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        apiService = RetrofitClient.getClient(ConnectivityInterceptor(requireActivity()))
        repository = MainMovieRepository(apiService)
        initViewModel()
        val movieAdapter = MoviePageListAdapter(requireContext())
        observeMovie(movieAdapter)
        initAdapter(movieAdapter)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (activity as BaseActivity).finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun initViewModel() {
        val factory = ViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(MainMovieViewModel::class.java)
    }

    private fun observeMovie(movieAdapter: MoviePageListAdapter) {
        viewModel.moviePagedList.observe(viewLifecycleOwner, {
            movieAdapter.submitList(it)
        })
        viewModel.networkState.observe(viewLifecycleOwner, {
            progress_bar.visibility =
                if (viewModel.listIsEmpty() && it == NetworkState.LOADING) View.VISIBLE else View.GONE
            error_message.visibility =
                if (viewModel.listIsEmpty() && it.statusNetwork == StatusNetwork.FAILED) View.VISIBLE else View.GONE
            if (!viewModel.listIsEmpty()) {
                movieAdapter.setNetworkState(it)
            }
        })
    }

    private fun initAdapter(movieAdapter: MoviePageListAdapter) {

        val gridLayoutManager = GridLayoutManager(this.context, 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {

            override fun getSpanSize(position: Int): Int {
                val viewType = movieAdapter.getItemViewType(position)
                return if (viewType == movieAdapter.DATA_VIEW_TYPE) 1
                else 3
            }
        }
        recycle_view_main_movie.layoutManager = gridLayoutManager
        recycle_view_main_movie.setHasFixedSize(true)
        recycle_view_main_movie.adapter = movieAdapter

        movieAdapter.addListener {
            val bundle = Bundle()
            bundle.getInt("1", it.id)
            openFragment(R.layout.details_movie_fragment, DetailsMovieFragment(),bundle)

            /*   val intent = Intent(context, DetailsMovieFragment::class.java)
        intent.putExtra("id", moviePoster?.id)
        context.startActivity(intent)*/
        }
    }

    class ViewModelFactory(private val repository: MainMovieRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainMovieViewModel(repository) as T
        }
    }
}