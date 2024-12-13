package com.example.tugaskpu1213

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tugaskpu1213.Database.Pemilih
import com.example.tugaskpu1213.Database.PemilihDAO
import com.example.tugaskpu1213.Database.PemilihDatabase
import com.example.tugaskpu1213.databinding.ActivityEditBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class EditDataPemilih : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private lateinit var dataPemilihDao: PemilihDAO
    private lateinit var executorService: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = PemilihDatabase.getDatabase(this)
        dataPemilihDao = db!!.PemilihDAO()
        executorService = Executors.newSingleThreadExecutor()

        val id = intent.getIntExtra("id", -1)
        val nama = intent.getStringExtra("nama")
        val nik = intent.getStringExtra("nik")
        val jenisKelamin = intent.getStringExtra("jenis_kelamin")
        val alamat = intent.getStringExtra("alamat")

        with(binding) {
            editNama.setText(nama)
            editNik.setText(nik)
            if (jenisKelamin == "Laki-laki") rgGender.check(radioEditlaki.id) else rgGender.check(radioEditperempuan.id)
            editAlamat.setText(alamat)

            btnSimpan.setOnClickListener {
                val updatedGender = if (rgGender.checkedRadioButtonId == radioEditlaki.id) "Laki-laki" else "Perempuan"
                val updatedData = Pemilih(
                    id = id,
                    nama = editNama.text.toString(),
                    nik = editNik.text.toString(),
                    jenisKelamin = updatedGender,
                    alamat = editAlamat.text.toString()
                )
                updateData(updatedData)
            }
        }
    }

    private fun updateData(dataPemilih: Pemilih) {
        executorService.execute {
            dataPemilihDao.update(dataPemilih)
            runOnUiThread {
                Toast.makeText(this, "Data berhasil diperbarui", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
