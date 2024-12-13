package com.example.tugaskpu1213.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PemilihDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(datapemilih: Pemilih)

    @Update
    fun update(datapemilih: Pemilih)

    @Delete
    fun delete(datapemilih: Pemilih)

    @Query("SELECT * from dataPemilih_table ORDER BY id ASC")
    fun getAllDataPemilih(): LiveData<List<Pemilih>>
}