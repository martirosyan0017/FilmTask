package com.example.telcelltask.movieapp.view.activity

import android.os.Bundle
import com.example.telcelltask.R
import com.example.telcelltask.movieapp.base.BaseActivity
import com.example.telcelltask.movieapp.view.fragment.SplashScreenFragment

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            openFragment(R.id.fragment_container, SplashScreenFragment(), null)
        }
    }
}