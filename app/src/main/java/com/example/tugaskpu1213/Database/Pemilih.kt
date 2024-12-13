package com.example.tugaskpu1213.Database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dataPemilih_table")
data class Pemilih (
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int=0,
    @ColumnInfo(name = "nama")
    val nama: String,
    @ColumnInfo(name = "NIK")
    val nik: String,
    @ColumnInfo(name = "alamat")
    val alamat: String,
    @ColumnInfo(name = "jenis_kelamin")
    val jenisKelamin: String
)