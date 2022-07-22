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
    private var selectedChoice: Choice? = null
    private var selectedChoiceCom: Choice? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.ivUserBatu.setOnClickListener {
            selectedChoice = Choice.Rock
            updateHandUI()
            Log.d(TAG, "onCreate: Anda Mengklik Batu")
        }
        binding.ivUserGunting.setOnClickListener {
            selectedChoice = Choice.Scissor
            updateHandUI()
            Log.d(TAG, "onCreate: Anda Mengklik Gunting")
        }
        binding.ivUserKertas.setOnClickListener {
            selectedChoice = Choice.Paper
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
        selectedChoice = null
        selectedChoiceCom = null
        val hasil = "VS"
        binding.tvHasil.text = hasil
        updateHandUI()
    }

    private fun updateHandUI() {
        when (selectedChoice) {
            Choice.Rock -> {
                binding.ivUserBatu.setBackgroundColor(Color.CYAN)
                binding.ivUserKertas.setBackgroundColor(Color.TRANSPARENT)
                binding.ivUserGunting.setBackgroundColor(Color.TRANSPARENT)
            }
            Choice.Paper -> {
                binding.ivUserKertas.setBackgroundColor(Color.CYAN)
                binding.ivUserBatu.setBackgroundColor(Color.TRANSPARENT)
                binding.ivUserGunting.setBackgroundColor(Color.TRANSPARENT)
            }
            Choice.Scissor -> {
                binding.ivUserKertas.setBackgroundColor(Color.TRANSPARENT)
                binding.ivUserBatu.setBackgroundColor(Color.TRANSPARENT)
                binding.ivUserGunting.setBackgroundColor(Color.CYAN)
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
        if (selectedChoice != null) {
            selectedChoiceCom = Choice.random()
        }
        when (selectedChoiceCom) {
            Choice.Rock -> {
                binding.ivComBatu.setBackgroundColor(Color.CYAN)
                binding.ivComKertas.setBackgroundColor(Color.TRANSPARENT)
                binding.ivComGunting.setBackgroundColor(Color.TRANSPARENT)
            }
            Choice.Paper -> {
                binding.ivComKertas.setBackgroundColor(Color.CYAN)
                binding.ivComBatu.setBackgroundColor(Color.TRANSPARENT)
                binding.ivComGunting.setBackgroundColor(Color.TRANSPARENT)
            }
            Choice.Scissor -> {
                binding.ivComKertas.setBackgroundColor(Color.TRANSPARENT)
                binding.ivComBatu.setBackgroundColor(Color.TRANSPARENT)
                binding.ivComGunting.setBackgroundColor(Color.CYAN)
            }
            else -> {
                binding.ivComKertas.setBackgroundColor(Color.TRANSPARENT)
                binding.ivComBatu.setBackgroundColor(Color.TRANSPARENT)
                binding.ivComGunting.setBackgroundColor(Color.TRANSPARENT)
            }

        }
        if (selectedChoice != null && selectedChoiceCom != null) {
            val hasil = startBattle(selectedChoice!!, selectedChoiceCom!!)
            binding.tvHasil.text = hasil
            Log.d(TAG, "updateHandUICom: $hasil")
        }
    }

    private fun startBattle(pemain1: Choice, pemain2: Choice): String {
        binding.tvHasil.setTextColor(Color.WHITE)
        binding.tvHasil.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28F)
        val p1Win = "PEMAIN 1 MENANG"
        val p2Win = "PEMAIN 2 MENANG"
        return when {
            pemain2 == pemain1 -> {
                binding.tvHasil.setBackgroundColor(Color.CYAN)
                "DRAW"
            }

            pemain1 == Choice.Rock -> {
                if (pemain2 == Choice.Paper) {
                    binding.tvHasil.setBackgroundColor(Color.GREEN)
                    p2Win
                } else {
                    binding.tvHasil.setBackgroundColor(Color.GREEN)
                    p1Win
                }
            }
            pemain1 == Choice.Paper -> {
                if (pemain2 == Choice.Scissor) {
                    binding.tvHasil.setBackgroundColor(Color.GREEN)
                    p2Win
                } else {
                    binding.tvHasil.setBackgroundColor(Color.GREEN)
                    p1Win
                }
            }
            pemain1 == Choice.Scissor -> {
                if (pemain2 == Choice.Rock) {
                    binding.tvHasil.setBackgroundColor(Color.GREEN)
                    p2Win
                } else {
                    binding.tvHasil.setBackgroundColor(Color.GREEN)
                    p1Win
                }

            }
            else -> ""
        }
    }
}