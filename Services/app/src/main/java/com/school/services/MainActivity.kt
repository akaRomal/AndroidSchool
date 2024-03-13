package com.school.services

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.school.services.databinding.ActivityMainBinding

const val TAG = "TESTTEST"

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val receiver = NetworkChangeReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "Отработал ОнКреате")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val intentFilter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(receiver, intentFilter)

        binding?.let {
            it.btnForegroundStart.setOnClickListener {
            }

            it.btnForegroundStop.setOnClickListener {
            }

            it.btnBackgroundStart.setOnClickListener {
                NetworkNotification(this, "test").execute()
            }
            it.btnBackgroundStop.setOnClickListener {  }
        }
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }

}