package com.example.myapplication.Service

import com.example.myapplication.model.PsicologoModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("psicologos")
    suspend fun obtenerPsicologo(): List<PsicologoModel>

    @POST("psicologos")
    suspend fun guardarPsicologo(@Body product: PsicologoModel): Response<Void>
}

object RetrofitClient {
    val api: ApiService by lazy {
        Retrofit.Builder()
            // 10.0.2.2 es el localhost de la PC para el emulador de Android
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}