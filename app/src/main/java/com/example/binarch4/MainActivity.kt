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
    private var playerChoice: Choice? = null
    private var enemyChoice: Choice? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.ivRockUser.setOnClickListener {
            playerChoice = Choice.Rock
            userChoiceUi()
            Log.d(TAG,
                "You Pick ${playerChoice.toString()} And Enemy Pick ${enemyChoice.toString()}")
        }
        binding.ivScissorUser.setOnClickListener {
            playerChoice = Choice.Scissor
            userChoiceUi()
            Log.d(TAG,
                "You Pick ${playerChoice.toString()} And Enemy Pick ${enemyChoice.toString()}")
        }
        binding.ivPaperUser.setOnClickListener {
            playerChoice = Choice.Paper
            userChoiceUi()
            Log.d(TAG,
                "You Pick ${playerChoice.toString()} And Enemy Pick ${enemyChoice.toString()}")
        }
        binding.ivReset.setOnClickListener {
            resetAll()

        }

    }

    private fun resetAll() {
        binding.tvResult.setTextColor(Color.RED)
        binding.tvResult.setTextSize(TypedValue.COMPLEX_UNIT_SP, 64F)
        binding.tvResult.setBackgroundColor(Color.TRANSPARENT)
        playerChoice = null
        enemyChoice = null
        val result = "VS"
        binding.tvResult.text = result
        userChoiceUi()
    }


    private fun userChoiceUi() {
        when (playerChoice) {
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
        if (playerChoice != null) {
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
        if (playerChoice != null && enemyChoice != null) {
            val result = startBattle(playerChoice!!, enemyChoice!!)
            binding.tvResult.text = result
            Log.i(TAG, "result: $result")
        }
    }

    private fun startBattle(playerChoice: Choice, enemyChoice: Choice): String {
        binding.tvResult.setTextColor(Color.WHITE)
        binding.tvResult.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24F)
        val p1Win = "PEMAIN 1 MENANG"
        val p2Win = "PEMAIN 2 MENANG"
        return when {
            enemyChoice == playerChoice -> {
                binding.tvResult.setBackgroundColor(Color.CYAN)
                "DRAW"
            }

            playerChoice == Choice.Rock -> {
                if (enemyChoice == Choice.Paper) {
                    binding.tvResult.setBackgroundColor(Color.GREEN)
                    p2Win
                } else {
                    binding.tvResult.setBackgroundColor(Color.GREEN)
                    p1Win
                }
            }
            playerChoice == Choice.Paper -> {
                if (enemyChoice == Choice.Scissor) {
                    binding.tvResult.setBackgroundColor(Color.GREEN)
                    p2Win
                } else {
                    binding.tvResult.setBackgroundColor(Color.GREEN)
                    p1Win
                }
            }
            playerChoice == Choice.Scissor -> {
                if (enemyChoice == Choice.Rock) {
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