package com.example.myapplication.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "psicologo_local")
data class PsicologoEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val username: String,
    val passwordHash:String
)