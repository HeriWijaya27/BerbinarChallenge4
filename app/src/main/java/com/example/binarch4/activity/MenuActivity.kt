package com.example.binarch4.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.binarch4.databinding.ActivityMenuBinding
import com.google.android.material.snackbar.Snackbar

class MenuActivity : AppCompatActivity() {

    companion object {
        const val NAME = "USER_INPUT"
    }

    private var binding: ActivityMenuBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        showSnackbar()

        val userName = intent.getStringExtra(NAME)
        val playerVsPlayer = "$userName VS PLAYER"
        val playerVsComputer = "$userName VS COMPUTER"

        binding?.apply {
            tvPemainVsPemain.text = playerVsPlayer.uppercase()
            tvPemainVsCpu.text = playerVsComputer.uppercase()
            ivLawanPemain.setOnClickListener {
                val versusPlayer = Intent(this@MenuActivity, VersusPlayerActivity::class.java)
                versusPlayer.putExtra(NAME, userName)
                startActivity(versusPlayer)
            }

            ivLawanCpu.setOnClickListener {
                val versusCpu = Intent(this@MenuActivity, VersusCpuActivity::class.java)
                versusCpu.putExtra(NAME, userName)
                startActivity(versusCpu)

            }
        }
    }

    private fun showSnackbar() {
        val welcomeText = intent.getStringExtra(NAME)?.uppercase()
        binding?.llMenuActivity?.let {
            Snackbar.make(
                it,
                "Welcome $welcomeText",
                Snackbar.LENGTH_LONG
            ).apply {
                view.setBackgroundColor(Color.BLACK)
                setTextColor(Color.WHITE)
                setAction("DISMISS") { dismiss() }
                show()
            }
        }

    }
}