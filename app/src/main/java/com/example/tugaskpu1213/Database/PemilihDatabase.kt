package com.example.tugaskpu1213.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Pemilih::class], version = 1, exportSchema = false)
abstract class PemilihDatabase : RoomDatabase() {
    abstract fun PemilihDAO(): PemilihDAO

    companion object {
        @Volatile
        private var INSTANCE: PemilihDatabase? = null
        fun getDatabase(context: Context): PemilihDatabase? {
            if (INSTANCE == null) {
                synchronized(PemilihDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PemilihDatabase::class.java,
                        "dataPemilih_table"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}