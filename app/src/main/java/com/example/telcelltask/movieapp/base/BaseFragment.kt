package com.example.telcelltask.movieapp.base

import android.os.Bundle
import androidx.fragment.app.Fragment

open class BaseFragment(layout: Int) : Fragment(layout) {

    fun openFragment(resId: Int, fragment: Fragment, bundle: Bundle? = null) {
        return (activity as BaseActivity).openFragment(resId, fragment, bundle)
    }

    open fun onRefresh() {}
}