package com.gmkornilov.shikimori.presentation.mainactivity

import android.net.ConnectivityManager
import android.net.Network
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmkornilov.shikimori.presentation.utils.SingleLiveEvent

class MainActivityViewModel(
    private val connectivityManager: ConnectivityManager,
): ViewModel() {
    private val _connectionRestored = SingleLiveEvent<Boolean>()
    val connectionRestored: LiveData<Boolean> = _connectionRestored

    private val _hasInternet = MutableLiveData(true)
    val hasInternet: LiveData<Boolean> = _hasInternet

    fun registerListener() {
        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)

                if (_hasInternet.value != true) {
                    _connectionRestored.postValue(true)
                }
                _hasInternet.postValue(true)
            }

            override fun onLost(network: Network) {
                super.onLost(network)

                _hasInternet.postValue(false)
            }

            override fun onUnavailable() {
                super.onUnavailable()

                _hasInternet.postValue(false)
            }
        })

    }
}