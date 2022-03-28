package com.emad.dummyproducts.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.flow.MutableStateFlow

class NetworkListener(private val context: Context): ConnectivityManager.NetworkCallback() {

    val isNetworkAvailable = MutableStateFlow(false)

    fun checkNetworkAvailability(): MutableStateFlow<Boolean>{
        val connectivityManager= context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerDefaultNetworkCallback(this)

        var isConnected= false



        connectivityManager.allNetworks.forEach {
            val networkCapability= connectivityManager.getNetworkCapabilities(it)
            networkCapability?.let {
                if (it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)){
                    isConnected= true
                    return@forEach
                }
            }
        }
        isNetworkAvailable.value= isConnected
        return isNetworkAvailable
    }

    override fun onAvailable(network: Network) {
        isNetworkAvailable.value= true
    }



    override fun onLost(network: Network) {
        isNetworkAvailable.value= false
    }
}