package com.example.binarch4

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import com.example.binarch4.databinding.ActivityMainBinding

class GamePlay: Activity() {
    private lateinit var binding: ActivityMainBinding


    val batuCom: Int = R.id.ivComBatu

    val kertasCom: Int = R.id.ivComKertas

    val guntingCom: Int = R.id.ivComGunting


    val choices = mutableListOf<Int>(batuCom,kertasCom,guntingCom)

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)




    }

    @SuppressLint("SetTextI18n")
    fun batu() {
        binding.ivUserBatu.setOnClickListener {
            binding.ivUserBatu.setBackgroundColor(Color.BLUE)
            when (choices.random()) {
                batuCom -> {
                    binding.ivComBatu.setBackgroundColor(Color.GREEN)
                    binding.tvHasil.text = "DRAW"
                }
                kertasCom -> {
                    binding.ivComKertas.setBackgroundColor(Color.GREEN)
                    binding.tvHasil.text = "COMPUTER WIN"
                }
                else -> {
                    binding.ivComGunting.setBackgroundColor(Color.GREEN)
                    binding.tvHasil.text = "PLAYER WIN"
                }
            }
        }
    }
    @SuppressLint("SetTextI18n")
    fun kertas() {
        binding.ivUserKertas.setOnClickListener {
            binding.ivUserKertas.setBackgroundColor(Color.BLUE)
            when (choices.random()) {
                batuCom -> {
                    binding.ivComBatu.setBackgroundColor(Color.GREEN)
                    binding.tvHasil.text = "PLAYER WIN"
                }
                kertasCom -> {
                    binding.ivComKertas.setBackgroundColor(Color.GREEN)
                    binding.tvHasil.text = "DRAW"
                }
                guntingCom -> {
                    binding.ivComGunting.setBackgroundColor(Color.GREEN)
                    binding.tvHasil.text = "COMPUTER WIN"
                }
            }
        }
    }
    @SuppressLint("SetTextI18n")
    fun gunting() {
        binding.ivUserGunting.setOnClickListener {
            binding.ivUserGunting.setBackgroundColor(Color.BLUE)
            when (choices.random()) {
                batuCom -> {
                    binding.ivComBatu.setBackgroundColor(Color.GREEN)
                    binding.tvHasil.text = "COMPUTER WIN"
                }
                kertasCom -> {
                    binding.ivComKertas.setBackgroundColor(Color.GREEN)
                    binding.tvHasil.text = "PLAYER WIN"
                }
                guntingCom -> {
                    binding.ivComGunting.setBackgroundColor(Color.GREEN)
                    binding.tvHasil.text = "DRAW"
                }
            }
        }
    }
}