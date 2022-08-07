package com.example.binarch4

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.binarch4.databinding.ActivityMenuBinding
import com.google.android.material.snackbar.Snackbar

class MenuActivity : AppCompatActivity() {


    private var binding : ActivityMenuBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val userName = intent.getStringExtra("KEY_NAME")
        val pemainVsPemain = "$userName VS PEMAIN"
        val pemainVsCpu = "$userName VS CPU"
        binding?.tvPemainVsPemain?.text = pemainVsPemain
        binding?.tvPemainVsCpu?.text = pemainVsCpu

        showSnackbar()

        binding?.apply {
            ivLawanPemain.setOnClickListener {
                val versusPemain = Intent(this@MenuActivity, VersusPemainActivity::class.java)
                versusPemain.putExtra("KEY_NAME", userName)
                startActivity(versusPemain)
                finish()
            }

            ivLawanCpu.setOnClickListener {
                val versusCpu = Intent(this@MenuActivity, VersusCpuActivity::class.java)
                versusCpu.putExtra("KEY_NAME", userName)
                startActivity(versusCpu)
                finish()
            }
        }
    }

    private fun showSnackbar() {
        val welcomeText = intent.getStringExtra("KEY_NAME")
        val snackbar = binding?.let {
            Snackbar.make(it.llMenuActivity,
                "Selamat Datang $welcomeText",
                Snackbar.LENGTH_LONG)
        }
        snackbar?.view?.setBackgroundColor(Color.BLACK)
        snackbar?.setTextColor(Color.WHITE)
        snackbar?.setAction("TUTUP") { snackbar.dismiss() }
        snackbar?.show()
    }
}