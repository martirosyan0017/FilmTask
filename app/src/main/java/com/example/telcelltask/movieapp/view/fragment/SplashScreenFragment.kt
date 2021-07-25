package com.example.telcelltask.movieapp.view.fragment

import android.os.Bundle
import android.view.View
import com.example.telcelltask.R
import com.example.telcelltask.movieapp.base.BaseFragment
import kotlinx.coroutines.*


class SplashScreenFragment : BaseFragment(R.layout.splash_screen_fragment) {

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleSplashScreen()
    }

    private fun handleSplashScreen() {
        activityScope.launch {
            delay(2000)
            openFragment(R.id.fragment_container, MainMovieFragment(), null)
        }
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }
}