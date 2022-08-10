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
        const val NAMA = "USER_INPUT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityGameplayBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding!!.root)
        val userName = intent.getStringExtra(NAMA)
        Glide.with(this)
            .load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
            .into(binding!!.ivBanner)

        binding?.apply {
            binding?.tvPlayerName?.text = userName.toString()

            ivRockUser.setOnClickListener {
                playerChoice = Choice.Batu
                userChoiceUi()
                infoLog()
                showToast()
            }
            ivScissorUser.setOnClickListener {
                playerChoice = Choice.Gunting
                userChoiceUi()
                infoLog()
                showToast()
            }
            ivPaperUser.setOnClickListener {
                playerChoice = Choice.Kertas
                userChoiceUi()
                infoLog()
                showToast()
            }
            ivReset.setOnClickListener {
                resetAll()
            }
            ivCloseVsCpu.setOnClickListener {
                finish()
            }
        }
    }

    private fun showToast() {
        val toast =
            Toast.makeText(this, "CPU Memilih ${enemyChoice.toString()}", Toast.LENGTH_SHORT)
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
            Choice.Batu -> {
                binding?.apply {
                    ivRockUser.setBackgroundResource(R.drawable.bg_item_selected)
                    ivPaperUser.setBackgroundColor(Color.TRANSPARENT)
                    ivScissorUser.setBackgroundColor(Color.TRANSPARENT)
                }
            }
            Choice.Kertas -> {
                binding?.apply {
                    ivPaperUser.setBackgroundResource(R.drawable.bg_item_selected)
                    ivRockUser.setBackgroundColor(Color.TRANSPARENT)
                    ivScissorUser.setBackgroundColor(Color.TRANSPARENT)
                }
            }
            Choice.Gunting -> {
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
            Choice.Batu -> {
                binding?.apply {
                    ivRockCom.setBackgroundResource(R.drawable.bg_item_selected)
                    ivPaperCom.setBackgroundColor(Color.TRANSPARENT)
                    ivScissorCom.setBackgroundColor(Color.TRANSPARENT)
                }
            }
            Choice.Kertas -> {
                binding?.apply {
                    ivPaperCom.setBackgroundResource(R.drawable.bg_item_selected)
                    ivRockCom.setBackgroundColor(Color.TRANSPARENT)
                    ivScissorCom.setBackgroundColor(Color.TRANSPARENT)
                }
            }
            Choice.Gunting -> {
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
        val playerName = intent.getStringExtra(NAMA)
        val pemainMenang = CustomResultDialog(result = "$playerName MENANG!", onReset = { resetAll() })
        val cpuMenang = CustomResultDialog(result = "CPU MENANG!", onReset = { resetAll() })
        val draw = CustomResultDialog(result = "SERI!", onReset = { resetAll() })


        if (enemyChoice == playerChoice) {
            draw.show(supportFragmentManager, "RESULT DIALOG")
            return
        }
        when (playerChoice) {
            Choice.Batu -> {
                if (enemyChoice == Choice.Kertas) {
                    cpuMenang.show(supportFragmentManager, "RESULT DIALOG")

                } else {
                    pemainMenang.show(supportFragmentManager, "RESULT DIALOG")
                }
            }
            Choice.Kertas -> {
                if (enemyChoice == Choice.Gunting) {
                    cpuMenang.show(supportFragmentManager, "RESULT DIALOG")
                } else {
                    pemainMenang.show(supportFragmentManager, "RESULT DIALOG")
                }
            }
            Choice.Gunting -> {
                if (enemyChoice == Choice.Batu) {
                    cpuMenang.show(supportFragmentManager, "RESULT DIALOG")
                } else {
                    pemainMenang.show(supportFragmentManager, "RESULT DIALOG")
                }
            }
        }
    }
}