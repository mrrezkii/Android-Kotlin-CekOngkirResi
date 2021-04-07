package com.kotlinmvvm.cekongkir.database.persistence

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class CekOngkirDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var instance: CekOngkirDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        CekOngkirDatabase::class.java, "CekOngkirLazday.db")
                        .allowMainThreadQueries()
                        .build()
    }
}