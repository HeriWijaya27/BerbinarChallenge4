package com.example.binarch4.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.binarch4.fragment.LandingPage1Fragment
import com.example.binarch4.fragment.LandingPage2Fragment
import com.example.binarch4.fragment.LandingPage3Fragment

class ViewPagerAdapter(fragmentActivity : FragmentActivity): FragmentStateAdapter(fragmentActivity) {

  private val pages = 3

    override fun getItemCount(): Int {
        return pages
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 ->{
                LandingPage1Fragment()
            }
            1 ->{
                LandingPage2Fragment()
            }
            2 -> LandingPage3Fragment()
            else ->{
                LandingPage1Fragment()
            }
        }
    }


}