package com.example.tugaskpu1213

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tugaskpu1213.databinding.ActivityDataPemilihBinding
import com.example.tugaskpu1213.databinding.ActivityDetailDataPemilihBinding

class DetailDataPemilih : AppCompatActivity() {
    private lateinit var binding: ActivityDetailDataPemilihBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDataPemilihBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data yang dikirim melalui Intent
        val nama = intent.getStringExtra("nama") ?: "Tidak tersedia"
        val nik = intent.getStringExtra("nik") ?: "Tidak tersedia"
        val jenisKelamin = intent.getStringExtra("jenis_kelamin") ?: "Tidak tersedia"
        val alamat = intent.getStringExtra("alamat") ?: "Tidak tersedia"

        // Tampilkan data pada TextView
        with(binding) {
            detailNama.text = "Nama: $nama"
            detailNik.text = "NIK: $nik"
            detailGender.text = "Gender: $jenisKelamin"
            detailAlamat.text = "Alamat: $alamat"

            // Fungsi tombol kembali
            btnSimpan.setOnClickListener { finish() }
        }
    }
}
