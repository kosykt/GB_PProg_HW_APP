package ru.kosykt.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.core.content.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.lang.ref.WeakReference

fun myNetworkStatus(_context: Context): LiveData<NetworkStatus> {
    val context = WeakReference(_context)
    val networkStatus = MutableLiveData<NetworkStatus>()
    val connectivityManager = context.get()?.getSystemService<ConnectivityManager>()
    val request = NetworkRequest.Builder().build()
    connectivityManager?.registerNetworkCallback(
        request,
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                networkStatus.postValue(NetworkStatus.AVAILABLE)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                networkStatus.postValue(NetworkStatus.LOST)
            }
        })
    return networkStatus
}

enum class NetworkStatus {
    AVAILABLE, LOST
}
