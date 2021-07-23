package com.example.telcelltask.movieapp.view.activity

import com.example.telcelltask.R
import com.example.telcelltask.movieapp.base.BaseActivity
import com.example.telcelltask.movieapp.view.fragment.MainMovieFragment
import com.example.telcelltask.movieapp.view.fragment.SplashScreenFragment

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun onStart() {
        super.onStart()
        supportActionBar?.title = getString(R.string.telcell_action_bar_title)
        supportActionBar?.subtitle = getString(R.string.movies_action_bar)
        openFragment(R.id.fragment_container, SplashScreenFragment(), null)
    }
}