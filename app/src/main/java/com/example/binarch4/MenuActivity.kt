package com.example.binarch4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.binarch4.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    var binding : ActivityMenuBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

    }
}