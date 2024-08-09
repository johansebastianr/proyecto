package com.example.proyecto.api.conductor

import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object AppConstantes {
    const val BASE_URL = "http://192.168.0.17:3000/"
}

interface WebConductor {

    @GET("conductores")
    suspend fun obtenerConductores(): Response<ConductorResponse>

    @GET("conductor/{idConductor}")
    suspend fun obtenerConductor(
        @Path("idConductor") idConductor: Int
    ): Response<ConductorClass>

    @POST("conductor/add")
    suspend fun agregarConductor(
        @Body conductor: ConductorClass
    ): Response<String>

    @PUT("conductor/update/{idConductor}")
    suspend fun actualizarConductor(
        @Path("idConductor") idConductor: Int,
        @Body conductor: ConductorClass
    ): Response<String>

    @DELETE("conductor/delete/{idConductor}")
    suspend fun borrarConductor(
        @Path("idConductor") idConductor: Int
    ): Response<String>


    object RetrofitClient {
        val webConductor: WebConductor by lazy {
            Retrofit.Builder()
                .baseUrl(AppConstantes.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(WebConductor::class.java)
        }
    }
}
