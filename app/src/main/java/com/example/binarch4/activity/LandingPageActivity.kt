package com.example.binarch4.activity

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.binarch4.R
import com.example.binarch4.adapter.ViewPagerAdapter
import com.example.binarch4.databinding.ActivityLandingPageViewBinding

class LandingPageActivity : AppCompatActivity() {
    private var binding: ActivityLandingPageViewBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingPageViewBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val viewPagerAdapter = ViewPagerAdapter(this)

        binding?.apply {
            vpLandingPage.adapter = viewPagerAdapter
            ciIndikator.setViewPager(binding?.vpLandingPage)

            vpLandingPage.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                val textPlay = "PLAY"
                val textNext = "NEXT"
                override fun onPageSelected(position: Int) {
                    if (position == 2) {
                        binding?.apply {
                            btnNextPage.text = textPlay
                            btnNextPage.setOnClickListener {
                                toMenu()
                            }
                        }
                    } else {
                        binding?.apply {
                            btnNextPage.text = textNext
                            btnNextPage.setBackgroundResource(R.drawable.bg_purple)
                            btnNextPage.setOnClickListener {
                                binding?.vpLandingPage?.apply {
                                    vpLandingPage.currentItem = vpLandingPage.currentItem + 1
                                }
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
