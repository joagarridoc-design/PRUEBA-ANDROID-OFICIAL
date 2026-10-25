package com.example.myapplication.data.local


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PsicologoDao {
    @Insert
    suspend fun insertarPsicologo(psicologo: PsicologoEntity)

    @Query("SELECT * FROM psicologo_local")
    fun obtenerPsicologos(): Flow<List<PsicologoEntity>>
}