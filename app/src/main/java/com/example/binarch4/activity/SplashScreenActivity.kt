package com.example.binarch4.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.binarch4.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private var binding : ActivitySplashScreenBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        Glide.with(this)
            .load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
            .into(binding!!.ivSplashImage)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LandingPageActivity::class.java))
            finish()
        }, 3000)

    }
}