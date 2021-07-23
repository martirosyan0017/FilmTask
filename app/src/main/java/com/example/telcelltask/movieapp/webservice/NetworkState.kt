package com.example.telcelltask.movieapp.webservice

import com.example.telcelltask.R
import com.example.telcelltask.movieapp.appconstants.StatusNetwork

class NetworkState(val statusNetwork: StatusNetwork, val msg: String) {

    companion object {
         val LOADED: NetworkState = NetworkState(StatusNetwork.SUCCESS, R.string.Success.toString())
         val LOADING: NetworkState = NetworkState(StatusNetwork.RUNNING, R.string.Running.toString())
    }
}