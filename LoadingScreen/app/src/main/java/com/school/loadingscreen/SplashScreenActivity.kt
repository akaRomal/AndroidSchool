package com.school.loadingscreen

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.school.loadingscreen.databinding.SplashScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private var binding: SplashScreenBinding? = null
    private var job: Job? = null

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        job = CoroutineScope(Dispatchers.IO)
            .launch {
                loading()
            }
    }

    override fun onDestroy() {
        binding = null
        job?.cancel()
        super.onDestroy()
    }

    private suspend fun loading() {
        var process = 0
        do {
            delay(50)
            process++
            binding?.progressBar?.progress = process
            if (process > 100)
                break
        } while (true)
        MainActivity.start(this)
    }

    private fun makeFullScreen(){
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()
    }
}