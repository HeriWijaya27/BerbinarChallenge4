package com.example.binarch4

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.binarch4.databinding.ActivityVersusCpuBinding
import com.example.binarch4.dialog.CustomResultDialog
import com.example.binarch4.modul.Choice

class VersusCpuActivity : AppCompatActivity() {
    private var binding: ActivityVersusCpuBinding? = null
    private var playerChoice: Choice? = null
    private var enemyChoice: Choice? = null

    companion object {
        const val NAMA = "KEY_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityVersusCpuBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding!!.root)
        val userName = intent.getStringExtra(NAMA)



        binding?.tvPlayerName?.text = userName.toString()

        binding?.apply {
            binding?.ivRockUser?.setOnClickListener {
                playerChoice = Choice.Batu
                userChoiceUi()
                infoLog()
                showToast()
            }
            binding?.ivScissorUser?.setOnClickListener {
                playerChoice = Choice.Gunting
                userChoiceUi()
                infoLog()
                showToast()
            }
            binding?.ivPaperUser?.setOnClickListener {
                playerChoice = Choice.Kertas
                userChoiceUi()
                infoLog()
                showToast()
            }
            binding?.ivReset?.setOnClickListener {
                resetAll()
            }
            binding?.ivCloseVsCpu?.setOnClickListener {
                val intent = Intent(this@VersusCpuActivity, MenuActivity::class.java)
                intent.putExtra(NAMA, userName)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun infoLog() {
        Log.d(TAG,
            "You Pick ${playerChoice.toString()} And Enemy Pick ${enemyChoice.toString()}")
    }

    private fun resetAll() {
        playerChoice = null
        enemyChoice = null
        userChoiceUi()
    }


    private fun userChoiceUi() {
        when (playerChoice) {
            Choice.Batu -> {
                binding?.ivRockUser?.setBackgroundResource(R.drawable.bg_item_selected)
                binding?.ivPaperUser?.setBackgroundColor(Color.TRANSPARENT)
                binding?.ivScissorUser?.setBackgroundColor(Color.TRANSPARENT)
            }
            Choice.Kertas -> {
                binding?.ivPaperUser?.setBackgroundResource(R.drawable.bg_item_selected)
                binding?.ivRockUser?.setBackgroundColor(Color.TRANSPARENT)
                binding?.ivScissorUser?.setBackgroundColor(Color.TRANSPARENT)
            }
            Choice.Gunting -> {
                binding?.ivPaperUser?.setBackgroundColor(Color.TRANSPARENT)
                binding?.ivRockUser?.setBackgroundColor(Color.TRANSPARENT)
                binding?.ivScissorUser?.setBackgroundResource(R.drawable.bg_item_selected)
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
            Choice.Batu -> {
                binding?.ivRockCom?.setBackgroundResource(R.drawable.bg_item_selected)
                binding?.ivPaperCom?.setBackgroundColor(Color.TRANSPARENT)
                binding?.ivScissorCom?.setBackgroundColor(Color.TRANSPARENT)
            }
            Choice.Kertas -> {
                binding?.ivPaperCom?.setBackgroundResource(R.drawable.bg_item_selected)
                binding?.ivRockCom?.setBackgroundColor(Color.TRANSPARENT)
                binding?.ivScissorCom?.setBackgroundColor(Color.TRANSPARENT)
            }
            Choice.Gunting -> {
                binding?.ivPaperCom?.setBackgroundColor(Color.TRANSPARENT)
                binding?.ivRockCom?.setBackgroundColor(Color.TRANSPARENT)
                binding?.ivScissorCom?.setBackgroundResource(R.drawable.bg_item_selected)
            }
            else -> {
                binding?.ivPaperCom?.setBackgroundColor(Color.TRANSPARENT)
                binding?.ivRockCom?.setBackgroundColor(Color.TRANSPARENT)
                binding?.ivScissorCom?.setBackgroundColor(Color.TRANSPARENT)
            }

        }
        if (playerChoice != null && enemyChoice != null) {
            showDialogResult(playerChoice!!, enemyChoice!!)
        }
    }


    private fun showDialogResult(playerChoice: Choice, enemyChoice: Choice): String {

        val playerName = intent.getStringExtra(NAMA)
        val pemainMenang = CustomResultDialog("$playerName Menang!")
        val cpuMenang = CustomResultDialog("CPU MENANG!")
        val draw = CustomResultDialog("SERI!")



        return when {

            enemyChoice == playerChoice -> {
                draw.show(supportFragmentManager, "CustomResultDialog").toString()

            }

            playerChoice == Choice.Batu -> {
                if (enemyChoice == Choice.Kertas) {
                    cpuMenang.show(supportFragmentManager, "CustomResultDialog").toString()

                } else {
                    pemainMenang.show(supportFragmentManager, "CustomResultDialog").toString()


                }
            }
            playerChoice == Choice.Kertas -> {
                if (enemyChoice == Choice.Gunting) {
                    cpuMenang.show(supportFragmentManager, "CustomResultDialog").toString()

                } else {
                    pemainMenang.show(supportFragmentManager, "CustomResultDialog").toString()

                }
            }
            playerChoice == Choice.Gunting -> {
                if (enemyChoice == Choice.Batu) {
                    cpuMenang.show(supportFragmentManager, "CustomResultDialog").toString()

                } else {
                    pemainMenang.show(supportFragmentManager, "CustomResultDialog").toString()

                }
            }
            else -> ""
        }

    }

    fun showToast() {
        val toast =
            Toast.makeText(this, "CPU Memilih ${enemyChoice.toString()}", Toast.LENGTH_SHORT)
        toast.show()
    }


}