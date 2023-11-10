package com.gumigames.presentation.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities

fun isConnectingNetwork(
    context: Context,
    onConnect: () -> Unit, //연결되어 있을 때 진행할 함수
    onNotConnect: () -> Unit //연결되어 있지 않을 때 진행할 함수
){
    val connectivityManager: ConnectivityManager =
        context.getSystemService(ConnectivityManager::class.java)
    val network: Network? = connectivityManager.activeNetwork
    val actNetwork: NetworkCapabilities? =
        connectivityManager.getNetworkCapabilities(network)

    if(actNetwork==null) onNotConnect()
    else {
        if (actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) onConnect()
        else if (actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) onConnect()
        else onNotConnect()
    }
}