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
    private var userChoice: Choice? = null
    private var enemyChoice: Choice? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.ivRockUser.setOnClickListener {
            userChoice = Choice.Rock
            userChoiceUi()
            Log.d(TAG, "onCreate: Anda Mengklik Batu")
        }
        binding.ivScissorUser.setOnClickListener {
            userChoice = Choice.Scissor
            userChoiceUi()
            Log.d(TAG, "onCreate: Anda Mengklik Gunting")
        }
        binding.ivPaperUser.setOnClickListener {
            userChoice = Choice.Paper
            userChoiceUi()
            Log.d(TAG, "onCreate: Anda Mengklik Kertas")
        }
        binding.ivReset.setOnClickListener {
            resetAll()
            Log.d(TAG, "onCreate: Anda Mengklik Reset")
        }

    }

    private fun resetAll() {
        binding.tvResult.setTextColor(Color.RED)
        binding.tvResult.setTextSize(TypedValue.COMPLEX_UNIT_SP, 64F)
        binding.tvResult.setBackgroundColor(Color.TRANSPARENT)
        userChoice = null
        enemyChoice = null
        val result = "VS"
        binding.tvResult.text = result
        userChoiceUi()
    }


    private fun userChoiceUi() {
        when (userChoice) {
            Choice.Rock -> {
                binding.ivRockUser.setBackgroundColor(Color.CYAN)
                binding.ivPaperUser.setBackgroundColor(Color.TRANSPARENT)
                binding.ivScissorUser.setBackgroundColor(Color.TRANSPARENT)
            }
            Choice.Paper -> {
                binding.ivPaperUser.setBackgroundColor(Color.CYAN)
                binding.ivRockUser.setBackgroundColor(Color.TRANSPARENT)
                binding.ivScissorUser.setBackgroundColor(Color.TRANSPARENT)
            }
            Choice.Scissor -> {
                binding.ivPaperUser.setBackgroundColor(Color.TRANSPARENT)
                binding.ivRockUser.setBackgroundColor(Color.TRANSPARENT)
                binding.ivScissorUser.setBackgroundColor(Color.CYAN)
            }
            else -> {
                binding.ivPaperUser.setBackgroundColor(Color.TRANSPARENT)
                binding.ivRockUser.setBackgroundColor(Color.TRANSPARENT)
                binding.ivScissorUser.setBackgroundColor(Color.TRANSPARENT)
            }
        }
        comChoiceUi()

    }

    private fun comChoiceUi() {
        if (userChoice != null) {
            enemyChoice = Choice.random()
        }
        when (enemyChoice) {
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
        if (userChoice != null && enemyChoice != null) {
            val result = startBattle(userChoice!!, enemyChoice!!)
            binding.tvResult.text = result
            Log.d(TAG, "updateHandUICom: $result")
        }
    }

    private fun startBattle(player: Choice, enemy: Choice): String {
        binding.tvResult.setTextColor(Color.WHITE)
        binding.tvResult.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28F)
        val p1Win = "PEMAIN 1 MENANG"
        val p2Win = "PEMAIN 2 MENANG"
        return when {
            enemy == player -> {
                binding.tvResult.setBackgroundColor(Color.CYAN)
                "DRAW"
            }

            player == Choice.Rock -> {
                if (enemy == Choice.Paper) {
                    binding.tvResult.setBackgroundColor(Color.GREEN)
                    p2Win
                } else {
                    binding.tvResult.setBackgroundColor(Color.GREEN)
                    p1Win
                }
            }
            player == Choice.Paper -> {
                if (enemy == Choice.Scissor) {
                    binding.tvResult.setBackgroundColor(Color.GREEN)
                    p2Win
                } else {
                    binding.tvResult.setBackgroundColor(Color.GREEN)
                    p1Win
                }
            }
            player == Choice.Scissor -> {
                if (enemy == Choice.Rock) {
                    binding.tvResult.setBackgroundColor(Color.GREEN)
                    p2Win
                } else {
                    binding.tvResult.setBackgroundColor(Color.GREEN)
                    p1Win
                }

            }
            else -> ""
        }
    }
}