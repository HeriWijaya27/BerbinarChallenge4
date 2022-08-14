package com.example.binarch4.activity

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.binarch4.R
import com.example.binarch4.adapter.ViewPagerAdapter
import com.example.binarch4.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {
    private var binding: ActivityOnBoardingBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val viewPagerAdapter = ViewPagerAdapter(this)

        binding?.apply {
            vpOnBoarding.adapter = viewPagerAdapter
            ciIndikator.setViewPager(binding?.vpOnBoarding)

            vpOnBoarding.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                val textPlay = "PLAY"
                val textNext = "NEXT"
                override fun onPageSelected(position: Int) {
                    if (position == 2) {
                        btnNextPage.text = textPlay
                        btnNextPage.setOnClickListener {
                            toMenu()
                        }
                    } else {
                        btnNextPage.text = textNext
                        btnNextPage.setBackgroundResource(R.drawable.bg_purple)
                        btnNextPage.setOnClickListener {
                            binding?.vpOnBoarding?.apply {
                                vpOnBoarding.currentItem = vpOnBoarding.currentItem + 1
                            }
                        }
                    }
                }
            })
        }
    }

    private fun toMenu() {
        val inputName = findViewById<EditText>(R.id.etUserName)
        val name = inputName.text.toString()
        val keMenu = Intent(this, MenuActivity::class.java)
        if (name.isNotEmpty()) {
            keMenu.putExtra("USER_INPUT", name)
            startActivity(keMenu)
            finish()
        } else {
            inputName.error = "NAME CANNOT BE EMPTY !"
            inputName.requestFocus()
        }
    }

    fun updateButton(isEnabled: Boolean) {
        if (isEnabled) {
            binding?.btnNextPage?.setBackgroundResource(R.drawable.bg_btn_orange)
        } else {
            binding?.btnNextPage?.setBackgroundResource(R.drawable.bg_purple)
        }
    }
}
