package com.example.telcelltask.movieapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseActivity(contentView: Int) : AppCompatActivity(contentView){

    fun openFragment(resId: Int, fragment: Fragment, bundle: Bundle? = null) {
        val existedFragment: Fragment? = supportFragmentManager.findFragmentByTag(fragment.javaClass.name)
        if (existedFragment != null)
            supportFragmentManager
                .beginTransaction()
                .remove(existedFragment)
                .commit()

        if (bundle != null)
            fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(resId, fragment, fragment.javaClass.name)
            .addToBackStack(null)
            .commit()
    }
}