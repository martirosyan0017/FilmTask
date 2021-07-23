package com.example.telcelltask.movieapp.view.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.telcelltask.movieapp.appconstants.StatusNetwork
import com.example.telcelltask.movieapp.webservice.NetworkState
import kotlinx.android.synthetic.main.network_state_item.view.*

class NetworkStateItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(networkState: NetworkState?) {
        if (networkState != null && networkState.statusNetwork == StatusNetwork.RUNNING) {
            itemView.progress_bar.visibility = View.VISIBLE
        } else {
            itemView.progress_bar.visibility = View.GONE
        }

        if (networkState != null && networkState.statusNetwork == StatusNetwork.FAILED) {
            itemView.error_msg.visibility = View.VISIBLE
            itemView.error_msg.text = networkState.msg
        } else {
            itemView.error_msg.visibility = View.GONE
        }
    }
}