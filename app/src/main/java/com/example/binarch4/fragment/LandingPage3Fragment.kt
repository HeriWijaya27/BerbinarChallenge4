package com.example.binarch4.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.binarch4.activity.LandingPageActivity
import com.example.binarch4.databinding.FragmentLandingPage3Binding


class LandingPage3Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentLandingPage3Binding.inflate(layoutInflater)
        binding.etUserName.addTextChangedListener {
            (activity as LandingPageActivity?)?.updateButton(!it.isNullOrEmpty())
        }
        return binding.root
    }
}