package com.example.tugaskpu1213

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tugaskpu1213.Database.Pemilih
import com.example.tugaskpu1213.Database.PemilihDAO
import com.example.tugaskpu1213.Database.PemilihDatabase
import com.example.tugaskpu1213.databinding.ActivityTambahDataBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class TambahData : AppCompatActivity() {
    private lateinit var binding: ActivityTambahDataBinding
    private lateinit var executorService: ExecutorService
    private lateinit var dataPemilihDao : PemilihDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        executorService = Executors.newSingleThreadExecutor()
        val db = PemilihDatabase.getDatabase(this)
        dataPemilihDao = db!!.PemilihDAO()
        with(binding){
            btnSimpan.setOnClickListener {
                val selectedGenderId = binding.rgGender.checkedRadioButtonId
                val gender = when (selectedGenderId) {
                    binding.radioLaki.id -> "Laki-laki"
                    binding.radioPerempuan.id -> "Perempuan"
                    else -> "Tidak ada yang dipilih"
                }
                // Validasi Input
                if (tambahNama.text.isNullOrBlank() ||
                    tambahNik.text.isNullOrBlank() ||
                    gender == "Tidak ada yang dipilih" ||
                    tambahAlamat.text.isNullOrBlank()
                ) {
                    // Jika data tidak valid
                    showToast("Semua data harus diisi dengan benar!")
                    return@setOnClickListener
                }
                insert(
                    Pemilih(
                        nama = tambahNama.text.toString(),
                        nik = tambahNik.text.toString(),
                        jenisKelamin = gender,
                        alamat = tambahAlamat.text.toString(),
                    )
                )
                showToast("Data berhasil disimpan!")
                setEmptyField()
                val startActivity = Intent(this@TambahData, MainDataPemilih::class.java)
                startActivity(startActivity)
                finish()
            }
        }
    }
    private fun insert(dataPemilih: Pemilih){
        executorService.execute {
            dataPemilihDao.insert(dataPemilih)
            runOnUiThread {
                val intent = Intent(this@TambahData, MainDataPemilih::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
    private fun showToast(message: String) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
    private fun setEmptyField() {
        with(binding) {
            tambahNama.text?.clear()
            tambahNik.text?.clear()
            rgGender.clearCheck()
            tambahAlamat.text?.clear()
        }
    }
}