package com.example.binarch4

import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import com.example.binarch4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var selectedHand: Hand? = null
    private var selectedHandCom: Hand? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.ivUserBatu.setOnClickListener {
            selectedHand = Hand.Batu
            updateHandUI()
            Log.d(TAG, "onCreate: Anda Mengklik Batu")
        }
        binding.ivUserGunting.setOnClickListener {
            selectedHand = Hand.Gunting
            updateHandUI()
            Log.d(TAG, "onCreate: Anda Mengklik Gunting")
        }
        binding.ivUserKertas.setOnClickListener {
            selectedHand = Hand.Kertas
            updateHandUI()
            Log.d(TAG, "onCreate: Anda Mengklik Kertas")
        }
        binding.ivReset.setOnClickListener {
            resetAll()
            Log.d(TAG, "onCreate: Anda Mengklik Reset")
        }

    }

    private fun resetAll() {
        binding.tvHasil.setTextColor(Color.RED)
        binding.tvHasil.setTextSize(TypedValue.COMPLEX_UNIT_SP, 64F)
        binding.tvHasil.setBackgroundColor(Color.TRANSPARENT)
        selectedHand = null
        selectedHandCom = null
        val hasil = "VS"
        binding.tvHasil.text = hasil
        updateHandUI()
    }

    private fun updateHandUI() {
        when (selectedHand) {
            Hand.Batu -> {
                binding.ivUserBatu.setBackgroundColor(Color.GREEN)
                binding.ivUserKertas.setBackgroundColor(Color.TRANSPARENT)
                binding.ivUserGunting.setBackgroundColor(Color.TRAN SPARENT)
            }
            Hand.Kertas -> {
                binding.ivUserKertas.setBackgroundColor(Color.GREEN)
                binding.ivUserBatu.setBackgroundColor(Color.TRANSPARENT)
                binding.ivUserGunting.setBackgroundColor(Color.TRANSPARENT)
            }
            Hand.Gunting -> {
                binding.ivUserKertas.setBackgroundColor(Color.TRANSPARENT)
                binding.ivUserBatu.setBackgroundColor(Color.TRANSPARENT)
                binding.ivUserGunting.setBackgroundColor(Color.GREEN)
            }
            else -> {
                binding.ivUserKertas.setBackgroundColor(Color.TRANSPARENT)
                binding.ivUserBatu.setBackgroundColor(Color.TRANSPARENT)
                binding.ivUserGunting.setBackgroundColor(Color.TRANSPARENT)
            }
        }
        updateHandUICom()

    }

    private fun updateHandUICom() {
        if (selectedHand != null) {
            selectedHandCom = Hand.random()
        }
        when (selectedHandCom) {
            Hand.Batu -> {
                binding.ivComBatu.setBackgroundColor(Color.BLUE)
                binding.ivComKertas.setBackgroundColor(Color.TRANSPARENT)
                binding.ivComGunting.setBackgroundColor(Color.TRANSPARENT)
            }
            Hand.Kertas -> {
                binding.ivComKertas.setBackgroundColor(Color.BLUE)
                binding.ivComBatu.setBackgroundColor(Color.TRANSPARENT)
                binding.ivComGunting.setBackgroundColor(Color.TRANSPARENT)
            }
            Hand.Gunting -> {
                binding.ivComKertas.setBackgroundColor(Color.TRANSPARENT)
                binding.ivComBatu.setBackgroundColor(Color.TRANSPARENT)
                binding.ivComGunting.setBackgroundColor(Color.BLUE)
            }
            else -> {
                binding.ivComKertas.setBackgroundColor(Color.TRANSPARENT)
                binding.ivComBatu.setBackgroundColor(Color.TRANSPARENT)
                binding.ivComGunting.setBackgroundColor(Color.TRANSPARENT)
            }

        }
        if (selectedHand != null && selectedHandCom != null) {
            val hasil = startBattle(selectedHand!!, selectedHandCom!!)
            binding.tvHasil.text = hasil
            Log.d(TAG, "updateHandUICom: $hasil")
        }
    }

    private fun startBattle(pemain1: Hand, pemain2: Hand): String {
        binding.tvHasil.setTextColor(Color.WHITE)
        binding.tvHasil.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28F)
        return when {
            pemain2 == pemain1 -> {
                binding.tvHasil.setBackgroundColor(Color.BLUE)
                "DRAW"
            }

            pemain1 == Hand.Batu -> {
                if (pemain2 == Hand.Kertas) {
                    binding.tvHasil.setBackgroundColor(Color.GREEN)
                    "PEMAIN 2 WIN"
                } else {
                    binding.tvHasil.setBackgroundColor(Color.GREEN)
                    "PEMAIN 1 WIN"
                }
            }
            pemain1 == Hand.Kertas -> {
                if (pemain2 == Hand.Gunting){
                    binding.tvHasil.setBackgroundColor(Color.GREEN)
                    "PEMAIN 2 WIN"
                } else {
                    binding.tvHasil.setBackgroundColor(Color.GREEN)
                    "PEMAIN 1 MENANG"
                }
            }
            pemain1 == Hand.Gunting -> {
                if (pemain2 == Hand.Batu){
                    binding.tvHasil.setBackgroundColor(Color.GREEN)
                    "PEMAIN 2 WIN"
                } else{
                    binding.tvHasil.setBackgroundColor(Color.GREEN)
                    "PEMAIN 1 WIN"
                }

            }
            else -> ""
        }
    }
}