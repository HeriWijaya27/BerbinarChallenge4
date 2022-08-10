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
        binding?.vpLandingPage?.adapter = viewPagerAdapter
        binding?.ciIndikator?.setViewPager(binding?.vpLandingPage)


        binding?.vpLandingPage?.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            val textPlay = "PLAY"
            val textNext = "NEXT"
            override fun onPageSelected(position: Int) {
                if(position == 2) {
                    binding?.tvNext?.text = textPlay
                    binding?.tvNext?.setOnClickListener {
                        toMenu()
                    }

                }else{
                    binding?.tvNext?.text = textNext
                    binding?.tvNext?.setOnClickListener {
                        binding?.vpLandingPage?.apply{
                            beginFakeDrag()
                            fakeDragBy(-3f)
                            endFakeDrag()
                        }
                    }
                }
            }
        })
    }

    private fun toMenu(){
        val inputName = findViewById<EditText>(R.id.etUserName)
        val name = inputName.text.toString()
        val keMenu = Intent(this, MenuActivity::class.java)
        if (name.isNotEmpty()) {
            keMenu.putExtra("USER_INPUT", name)
            startActivity(keMenu)
        } else {
            inputName.error = "NAMA TIDAK BOLEH KOSONG"
            inputName.requestFocus()
        }
    }
}
