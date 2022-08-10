package com.example.binarch4.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.binarch4.databinding.ActivityMenuBinding
import com.google.android.material.snackbar.Snackbar

class MenuActivity : AppCompatActivity() {

    companion object {
        const val NAMA = "USER_INPUT"
    }

    private var binding: ActivityMenuBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        showSnackbar()

        val userName = intent.getStringExtra(NAMA)
        val pemainVsPemain = "$userName VS PEMAIN"
        val pemainVsCpu = "$userName VS CPU"

        binding?.apply {
            binding?.tvPemainVsPemain?.text = pemainVsPemain
            binding?.tvPemainVsCpu?.text = pemainVsCpu
            ivLawanPemain.setOnClickListener {
                val versusPemain = Intent(this@MenuActivity, VersusPemainActivity::class.java)
                versusPemain.putExtra(NAMA, userName)
                startActivity(versusPemain)
            }

            ivLawanCpu.setOnClickListener {
                val versusCpu = Intent(this@MenuActivity, VersusCpuActivity::class.java)
                versusCpu.putExtra(NAMA, userName)
                startActivity(versusCpu)

            }
        }
    }

    private fun showSnackbar() {
        val welcomeText = intent.getStringExtra(NAMA)
        binding?.llMenuActivity?.let {
            Snackbar.make(
                it,
                "Selamat Datang $welcomeText",
                Snackbar.LENGTH_LONG
            ).apply {
                view.setBackgroundColor(Color.BLACK)
                setTextColor(Color.WHITE)
                setAction("TUTUP") { dismiss() }
                show()
            }
        }

    }
}