package com.example.myapplication.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PsicologoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun psicologoDao(): PsicologoDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null // Usar null estándar en Kotlin

        fun obtenerBaseDeDatos(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mpaz_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
