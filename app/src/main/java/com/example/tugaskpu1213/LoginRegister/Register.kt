package com.example.tugaskpu1213.LoginRegister

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tugaskpu1213.MainDataPemilih
import com.example.tugaskpu1213.PrefManager
import com.example.tugaskpu1213.R
import com.example.tugaskpu1213.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var prefManager: PrefManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefManager = PrefManager.getInstance(this)
        with(binding) {
            btnRegister.setOnClickListener {
                val username = registerUsername.text.toString()
                val password = registerPassword.text.toString()
                val confirmPassword = confirmRegisterPassword.text.toString()
                if (username.isEmpty() || password.isEmpty() ||
                    confirmPassword.isEmpty()) {
                    Toast.makeText(
                        this@Register,
                        "Mohon isi semua data",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (password != confirmPassword) {
                    Toast.makeText(this@Register, "Password tidak sama",
                        Toast.LENGTH_SHORT)
                        .show()
                } else {
                    prefManager.saveUsername(username)
                    prefManager.savePassword(password)
                    prefManager.setLoggedIn(true)
                    checkLoginStatus()
                }
            }
            menujuLogin.setOnClickListener {
                startActivity(Intent(this@Register, MainActivity::class.java))
            }
        }
    }
    private fun checkLoginStatus() {
        val isLoggedIn = prefManager.isLoggedIn()
        if (isLoggedIn) {
            Toast.makeText(this@Register, "Registrasi berhasil",
                Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@Register, MainDataPemilih::class.java))
            finish()
        } else {
            Toast.makeText(this@Register, "Registrasi gagal",
                Toast.LENGTH_SHORT).show()
        }
    }
}
