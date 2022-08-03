package com.example.binarch4

import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import com.example.binarch4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var playerChoice: Choice? = null
    private var enemyChoice: Choice? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding!!.root)

        binding?.ivRockUser?.setOnClickListener {
            playerChoice = Choice.Rock
            userChoiceUi()
            infoLog()
        }
        binding?.ivScissorUser?.setOnClickListener {
            playerChoice = Choice.Scissor
            userChoiceUi()
            infoLog()
        }
        binding?.ivPaperUser?.setOnClickListener {
            playerChoice = Choice.Paper
            userChoiceUi()
            infoLog()
        }
        binding?.ivReset?.setOnClickListener {
            resetAll()

        }

    }

    private fun infoLog(){
       Log.d(TAG,
            "You Pick ${playerChoice.toString()} And Enemy Pick ${enemyChoice.toString()}")
    }
    private fun resetAll() {
        binding?.tvResult?.setTextColor(Color.RED)
        binding?.tvResult?.setTextSize(TypedValue.COMPLEX_UNIT_SP, 80F)
        binding?.tvResult?.setBackgroundColor(Color.TRANSPARENT)
        playerChoice = null
        enemyChoice = null
        val result = "VS"
        binding?.tvResult?.text = result
        userChoiceUi()
    }


    private fun userChoiceUi() {
        when (playerChoice) {
            Choice.Rock -> {
                binding?.ivRockUser?.setBackgroundColor(Color.CYAN)
                binding?.ivPaperUser?.setBackgroundColor(Color.TRANSPARENT)
                binding?.ivScissorUser?.setBackgroundColor(Color.TRANSPARENT)
            }
            Choice.Paper -> {
                binding?.ivPaperUser?.setBackgroundColor(Color.CYAN)
                binding?.ivRockUser?.setBackgroundColor(Color.TRANSPARENT)
                binding?.ivScissorUser?.setBackgroundColor(Color.TRANSPARENT)
            }
            Choice.Scissor -> {
                binding?.ivPaperUser?.setBackgroundColor(Color.TRANSPARENT)
                binding?.ivRockUser?.setBackgroundColor(Color.TRANSPARENT)
                binding?.ivScissorUser?.setBackgroundColor(Color.CYAN)
            }
            else -> {
                binding?.ivPaperUser?.setBackgroundColor(Color.TRANSPARENT)
                binding?.ivRockUser?.setBackgroundColor(Color.TRANSPARENT)
                binding?.ivScissorUser?.setBackgroundColor(Color.TRANSPARENT)
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
                binding?.ivRockCom?.setBackgroundColor(Color.CYAN)
                binding?.ivPaperCom?.setBackgroundColor(Color.TRANSPARENT)
                binding?.ivScissorCom?.setBackgroundColor(Color.TRANSPARENT)
            }
            Choice.Paper -> {
                binding?.ivPaperCom?.setBackgroundColor(Color.CYAN)
                binding?.ivRockCom?.setBackgroundColor(Color.TRANSPARENT)
                binding?.ivScissorCom?.setBackgroundColor(Color.TRANSPARENT)
            }
            Choice.Scissor -> {
                binding?.ivPaperCom?.setBackgroundColor(Color.TRANSPARENT)
                binding?.ivRockCom?.setBackgroundColor(Color.TRANSPARENT)
                binding?.ivScissorCom?.setBackgroundColor(Color.CYAN)
            }
            else -> {
                binding?.ivPaperCom?.setBackgroundColor(Color.TRANSPARENT)
                binding?.ivRockCom?.setBackgroundColor(Color.TRANSPARENT)
                binding?.ivScissorCom?.setBackgroundColor(Color.TRANSPARENT)
            }

        }
        if (playerChoice != null && enemyChoice != null) {
            val result = startBattle(playerChoice!!, enemyChoice!!)
            binding?.tvResult?.text = result
            Log.i(TAG, "result: $result")
        }
    }

    private fun startBattle(playerChoice: Choice, enemyChoice: Choice): String {
        binding?.tvResult?.setTextColor(Color.WHITE)
        binding?.tvResult?.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24F)
        val player1Win = "PEMAIN 1 MENANG"
        val player2Win = "PEMAIN 2 MENANG"
        return when {
            enemyChoice == playerChoice -> {
                binding?.tvResult?.setBackgroundColor(Color.CYAN)
                "DRAW"
            }

            playerChoice == Choice.Rock -> {
                if (enemyChoice == Choice.Paper) {
                    binding?.tvResult?.setBackgroundColor(Color.GREEN)
                    player2Win
                } else {
                    binding?.tvResult?.setBackgroundColor(Color.GREEN)
                    player1Win
                }
            }
            playerChoice == Choice.Paper -> {
                if (enemyChoice == Choice.Scissor) {
                    binding?.tvResult?.setBackgroundColor(Color.GREEN)
                    player2Win
                } else {
                    binding?.tvResult?.setBackgroundColor(Color.GREEN)
                    player1Win
                }
            }
            playerChoice == Choice.Scissor -> {
                if (enemyChoice == Choice.Rock) {
                    binding?.tvResult?.setBackgroundColor(Color.GREEN)
                    player2Win
                } else {
                    binding?.tvResult?.setBackgroundColor(Color.GREEN)
                    player1Win
                }

            }
            else -> ""
        }
    }
}