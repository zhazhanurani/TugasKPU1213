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
import com.example.tugaskpu1213.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var prefManager : PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefManager = PrefManager.getInstance(this)

        with(binding){
            btnLogin.setOnClickListener {
                val username = loginUsername.text.toString()
                val password = loginPassword.text.toString()
                if (username.isEmpty() || password.isEmpty())
                    Toast.makeText(
                        this@MainActivity,
                        "Isi Semua Datamu",
                        Toast.LENGTH_SHORT
                    ).show()
                else {
                    if (isValidUsernamePassword()){
                        prefManager.setLoggedIn(true)
                        checkLoginStatus()
                    }
                    else {
                        if (isValidUsernamePassword()) {
                            prefManager.setLoggedIn(true)
                            checkLoginStatus()
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "Username atau Password salah",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
            menujuRegister.setOnClickListener {
                startActivity(Intent(this@MainActivity, Register::class.java))
            }
        }


    }
    private fun isValidUsernamePassword(): Boolean{
        val username = prefManager.getUsername()
        val password = prefManager.getPassword()
        val inputUsername = binding.loginUsername.text.toString()
        val inputPassword = binding.loginPassword.text.toString()
        return username == inputUsername && password == inputPassword
    }

    private fun checkLoginStatus() {
        val isLoggedIn = prefManager.isLoggedIn()
        if (isLoggedIn) {
            Toast.makeText(this@MainActivity, "Login berhasil",
                Toast.LENGTH_SHORT).show()
            startActivity(
                Intent(this@MainActivity,
                    MainDataPemilih::class.java)
            )
            finish()
        } else {
            Toast.makeText(this@MainActivity, "Login gagal",
                Toast.LENGTH_SHORT).show()
        }
    }

}