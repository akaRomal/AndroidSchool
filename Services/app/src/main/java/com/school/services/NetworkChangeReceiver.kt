package com.school.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log

class NetworkChangeReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        Log.d(TAG, "Сработал ресивер")
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            Log.d(TAG, "Сеть доступна")
            NetworkNotification(context, "Сеть доступна").execute()
        } else {
            Log.d(TAG, "Сеть недоступна")
            NetworkNotification(context, "Сеть недоступна").execute()
        }
    }
}