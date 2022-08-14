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

class VersusPlayerActivity : AppCompatActivity() {
    private var binding: ActivityGameplayBinding? = null
    private var player1Choice: Choice? = null
    private var player2Choice: Choice? = null

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
            tvPlayerName.text = userName.toString()
            ivRockUser.setOnClickListener {
                player1Choice = Choice.ROCK
                showResultUi()
                infoLog()
            }
            ivScissorUser.setOnClickListener {
                player1Choice = Choice.SCISSOR
                showResultUi()
                infoLog()
            }
            ivPaperUser.setOnClickListener {
                player1Choice = Choice.PAPER
                showResultUi()
                infoLog()
            }
            ivPaperCom.setOnClickListener {
                player2Choice = Choice.PAPER
                showResultUi()
                infoLog()
            }
            ivScissorCom.setOnClickListener {
                player2Choice = Choice.SCISSOR
                showResultUi()
                infoLog()
            }
            ivRockCom.setOnClickListener {
                player2Choice = Choice.ROCK
                showResultUi()
                infoLog()
            }
            ivReset.setOnClickListener {
                if (player1Choice != null){
                    val toast = Toast.makeText(this@VersusPlayerActivity,
                        "$userName's Choice Has Been Reset.Please Choose Again !",
                        Toast.LENGTH_SHORT)
                    toast.show()
                    resetAll()
                }
            }
            ivClose.setOnClickListener {
                finish()
            }
        }
    }

    private fun showResultUi() {

        if (player1Choice != null && player2Choice != null) {
            showDialogResult(player1Choice!!, player2Choice!!)
            userChoiceUi()
            comChoiceUi()
            showToastPlayer2()
        }

    }

    private fun showToastPlayer2() {
        val toast =
            Toast.makeText(this, "PLAYER 2 CHOOSE ${player2Choice.toString()}", Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun infoLog() {
        Log.d(
            TAG,
            "You Pick ${player1Choice.toString()} And Enemy Pick ${player2Choice.toString()}"
        )
    }

    private fun resetAll() {
        player1Choice = null
        player2Choice = null
        userChoiceUi()
        comChoiceUi()
    }

    private fun userChoiceUi() {
        when (player1Choice) {
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
    }

    private fun comChoiceUi() {
        when (player2Choice) {
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

    }


    private fun showDialogResult(playerChoice: Choice, enemyChoice: Choice) {
        val playerName = intent.getStringExtra(NAME)?.uppercase()
        val player1Winning =
            CustomResultDialog(result = "$playerName WIN !", onReset = { resetAll() })
        val player2Winning = CustomResultDialog(result = "PLAYER 2 WIN !", onReset = { resetAll() })
        val draw = CustomResultDialog(result = "DRAW !", onReset = { resetAll() })

        if (enemyChoice == playerChoice) {
            draw.show(supportFragmentManager, "RESULT DIALOG")
            return
        }
        when (playerChoice) {
            Choice.ROCK -> {
                if (enemyChoice == Choice.PAPER) {
                    player2Winning.show(supportFragmentManager, "RESULT DIALOG")

                } else {
                    player1Winning.show(supportFragmentManager, "RESULT DIALOG")
                }
            }
            Choice.PAPER -> {
                if (enemyChoice == Choice.SCISSOR) {
                    player2Winning.show(supportFragmentManager, "RESULT DIALOG")
                } else {
                    player1Winning.show(supportFragmentManager, "RESULT DIALOG")
                }
            }
            Choice.SCISSOR -> {
                if (enemyChoice == Choice.ROCK) {
                    player2Winning.show(supportFragmentManager, "RESULT DIALOG")
                } else {
                    player1Winning.show(supportFragmentManager, "RESULT DIALOG")
                }
            }
        }
    }
}