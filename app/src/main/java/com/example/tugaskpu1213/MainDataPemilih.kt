package com.example.tugaskpu1213

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugaskpu1213.Database.Pemilih
import com.example.tugaskpu1213.Database.PemilihDAO
import com.example.tugaskpu1213.Database.PemilihDatabase
import com.example.tugaskpu1213.LoginRegister.MainActivity
import com.example.tugaskpu1213.databinding.ActivityDataPemilihBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainDataPemilih : AppCompatActivity() {
    private lateinit var binding: ActivityDataPemilihBinding
    private lateinit var prefManager: PrefManager
    private lateinit var dataPemilihDao: PemilihDAO
    private lateinit var executorService: ExecutorService
    private lateinit var adapter: PemilihAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDataPemilihBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // inisialisasi pref manager
        prefManager = PrefManager.getInstance(this)
        checkLoginStatus()

        // inisialisasi DAO untuk CRUD
        executorService = Executors.newSingleThreadExecutor()
        val db = PemilihDatabase.getDatabase(this)
        dataPemilihDao = db!!.PemilihDAO()


        // inisialisasi Recycler View
        setupRecyclerView()

        with(binding){
            btnAddData.setOnClickListener{
                val startActivity = Intent(this@MainDataPemilih, TambahData::class.java)
                startActivity(startActivity)
            }

            btnLogout.setOnClickListener{
                prefManager.clear()
                finish()
            }
        }
    }
    // Fungsi Setup Recycler View
    private fun setupRecyclerView(){
        binding.listView.layoutManager = LinearLayoutManager(this)

        adapter = PemilihAdapter(this){ data ->
            deleteData(data)
        }
        binding.listView.adapter = adapter

        // Mengamati Perubahan Data LiveData
        dataPemilihDao.getAllDataPemilih().observe(this) {datalist ->
            adapter.submitList(datalist)
        }
    }
    private fun deleteData(dataPemilih: Pemilih) {
        executorService.execute {
            dataPemilihDao.delete(dataPemilih)
            runOnUiThread {
                Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun checkLoginStatus() {
        val isLoggedIn = prefManager.isLoggedIn()
        if (!isLoggedIn) {
            startActivity(Intent(this@MainDataPemilih, MainActivity::class.java))
            finish()
        }
    }

}