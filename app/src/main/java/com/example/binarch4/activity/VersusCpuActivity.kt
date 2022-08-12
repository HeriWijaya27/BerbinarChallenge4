package com.example.binarch4.activity

import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.binarch4.R
import com.example.binarch4.databinding.ActivityGameplayBinding
import com.example.binarch4.dialog.CustomResultDialog
import com.example.binarch4.modul.Choice

class VersusCpuActivity : AppCompatActivity() {
    private var binding: ActivityGameplayBinding? = null
    private var playerChoice: Choice? = null
    private var enemyChoice: Choice? = null

    companion object {
        const val NAME = "USER_INPUT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityGameplayBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding!!.root)
        val userName = intent.getStringExtra(NAME)?.uppercase()
        Glide.with(this)
            .load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
            .into(binding!!.ivBanner)

        binding?.apply {
            binding?.tvPlayerName?.text = userName.toString()

            ivRockUser.setOnClickListener {
                playerChoice = Choice.ROCK
                userChoiceUi()
                infoLog()
                showToast()
            }
            ivScissorUser.setOnClickListener {
                playerChoice = Choice.SCISSOR
                userChoiceUi()
                infoLog()
                showToast()
            }
            ivPaperUser.setOnClickListener {
                playerChoice = Choice.PAPER
                userChoiceUi()
                infoLog()
                showToast()
            }
            ivReset.setOnClickListener {
                if (playerChoice != null && enemyChoice != null) {
                    val toast = Toast.makeText(this@VersusCpuActivity,
                        "$userName's & CPU's Choice Has Been Reset.Please Choose Again !",
                        Toast.LENGTH_SHORT)
                    toast.show()
                    resetAll()
                }
            }
            ivCloseVsCpu.setOnClickListener {
                finish()
            }
        }
    }

    private fun showToast() {
        val toast =
            Toast.makeText(this, "COMPUTER CHOOSE ${enemyChoice.toString()}", Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun infoLog() {
        Log.d(
            TAG,
            "You Pick ${playerChoice.toString()} And Enemy Pick ${enemyChoice.toString()}"
        )
    }

    private fun resetAll() {
        playerChoice = null
        enemyChoice = null
        userChoiceUi()
        comChoiceUi()
    }

    private fun userChoiceUi() {
        when (playerChoice) {
            Choice.ROCK -> {
                binding?.apply {
                    ivRockUser.setBackgroundResource(R.drawable.bg_item_selected)
                    ivPaperUser.setBackgroundColor(Color.TRANSPARENT)
                    ivScissorUser.setBackgroundColor(Color.TRANSPARENT)
                }
            }
            Choice.PAPER -> {
                binding?.apply {
                    ivPaperUser.setBackgroundResource(R.drawable.bg_item_selected)
                    ivRockUser.setBackgroundColor(Color.TRANSPARENT)
                    ivScissorUser.setBackgroundColor(Color.TRANSPARENT)
                }
            }
            Choice.SCISSOR -> {
                binding?.apply {
                    ivPaperUser.setBackgroundColor(Color.TRANSPARENT)
                    ivRockUser.setBackgroundColor(Color.TRANSPARENT)
                    ivScissorUser.setBackgroundResource(R.drawable.bg_item_selected)
                }
            }
            else -> {
                binding?.apply {
                    ivPaperUser.setBackgroundColor(Color.TRANSPARENT)
                    ivRockUser.setBackgroundColor(Color.TRANSPARENT)
                    ivScissorUser.setBackgroundColor(Color.TRANSPARENT)
                }
            }
        }
        comChoiceUi()
    }

    private fun comChoiceUi() {
        if (playerChoice != null) {
            enemyChoice = Choice.random()
        }
        when (enemyChoice) {
            Choice.ROCK -> {
                binding?.apply {
                    ivRockCom.setBackgroundResource(R.drawable.bg_item_selected)
                    ivPaperCom.setBackgroundColor(Color.TRANSPARENT)
                    ivScissorCom.setBackgroundColor(Color.TRANSPARENT)
                }
            }
            Choice.PAPER -> {
                binding?.apply {
                    ivPaperCom.setBackgroundResource(R.drawable.bg_item_selected)
                    ivRockCom.setBackgroundColor(Color.TRANSPARENT)
                    ivScissorCom.setBackgroundColor(Color.TRANSPARENT)
                }
            }
            Choice.SCISSOR -> {
                binding?.apply {
                    ivPaperCom.setBackgroundColor(Color.TRANSPARENT)
                    ivRockCom.setBackgroundColor(Color.TRANSPARENT)
                    ivScissorCom.setBackgroundResource(R.drawable.bg_item_selected)
                }
            }
            else -> {
                binding?.apply {
                    ivPaperCom.setBackgroundColor(Color.TRANSPARENT)
                    ivRockCom.setBackgroundColor(Color.TRANSPARENT)
                    ivScissorCom.setBackgroundColor(Color.TRANSPARENT)
                }
            }
        }
        if (playerChoice != null && enemyChoice != null) {
            showDialogResult(playerChoice!!, enemyChoice!!)
        }
    }

    private fun showDialogResult(playerChoice: Choice, enemyChoice: Choice) {
        val playerName = intent.getStringExtra(NAME)?.uppercase()
        val playerWinning = CustomResultDialog(result = "$playerName WIN !", onReset = { resetAll() })
        val cpuWinning = CustomResultDialog(result = "COMPUTER WIN !", onReset = { resetAll() })
        val draw = CustomResultDialog(result = "DRAW !", onReset = {resetAll()})


        if (enemyChoice == playerChoice) {
            draw.show(supportFragmentManager, "RESULT DIALOG")
            return
        }
        when (playerChoice) {
            Choice.ROCK -> {
                if (enemyChoice == Choice.PAPER) {
                    cpuWinning.show(supportFragmentManager, "RESULT DIALOG")

                } else {
                    playerWinning.show(supportFragmentManager, "RESULT DIALOG")
                }
            }
            Choice.PAPER -> {
                if (enemyChoice == Choice.SCISSOR) {
                    cpuWinning.show(supportFragmentManager, "RESULT DIALOG")
                } else {
                    playerWinning.show(supportFragmentManager, "RESULT DIALOG")
                }
            }
            Choice.SCISSOR -> {
                if (enemyChoice == Choice.ROCK) {
                    cpuWinning.show(supportFragmentManager, "RESULT DIALOG")
                } else {
                    playerWinning.show(supportFragmentManager, "RESULT DIALOG")
                }
            }
        }
    }
}