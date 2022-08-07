package com.example.binarch4.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.binarch4.databinding.ActivityMenuBinding
import com.example.binarch4.modul.User
import com.google.android.material.snackbar.Snackbar

class MenuActivity : AppCompatActivity() {

companion object{
    const val NAMA = "USER_INPUT"
}
    private var binding : ActivityMenuBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val userName = intent.getStringExtra(NAMA)
        val pemainVsPemain = "$userName VS PEMAIN"
        val pemainVsCpu = "$userName VS CPU"
        showSnackbar()

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