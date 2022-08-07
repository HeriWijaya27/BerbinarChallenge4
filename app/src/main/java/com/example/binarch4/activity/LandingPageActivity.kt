package com.example.binarch4.activity

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.binarch4.R
import com.example.binarch4.adapter.ViewPagerAdapter
import com.example.binarch4.databinding.ActivityLandingPageViewBinding
import me.relex.circleindicator.CircleIndicator3

class LandingPageActivity : AppCompatActivity() {
    private var binding: ActivityLandingPageViewBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingPageViewBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val halaman = findViewById<ViewPager2>(R.id.vpLandingPage)
        val viewPagerAdapter = ViewPagerAdapter(this)
        halaman.adapter = viewPagerAdapter
        val indicator = findViewById<CircleIndicator3>(R.id.ciIndikator)
        indicator.setViewPager(halaman)


        binding?.ivNext?.setOnClickListener {
            val nameInput = findViewById<EditText>(R.id.etUserName).text.toString()
            val enterGame = Intent(this, MenuActivity::class.java)
            enterGame.putExtra("USER_INPUT", nameInput)
            startActivity(enterGame)
        }


    }
}
