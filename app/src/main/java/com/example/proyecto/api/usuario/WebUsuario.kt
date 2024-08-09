package com.example.proyecto.api.usuario

import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object AppConstantes {
    const val BASE_URL = "http://192.168.0.17:3000/"
}

interface WebUsuario {

    @GET("usuarios")
    suspend fun obtenerUsuarios(): Response<UsuariosResponse>

    @GET("usuario/{idUsuario}")
    suspend fun obtenerUsuario(
        @Path("idUsuario") idUsuario: Int
    ): Response<UsuarioClass>

    @POST("usuario/add")
    suspend fun agregarUsuario(
        @Body usuario: UsuarioClass
    ): Response<String>

    @PUT("usuario/update/{idUsuario}")
    suspend fun actualizarUsuario(
        @Path("idUsuario") idUsuario: Int,
        @Body usuario: UsuarioClass
    ): Response<String>

    @DELETE("usuario/delete/{idUsuario}")
    suspend fun borrarUsuario(
        @Path("idUsuario") idUsuario: Int
    ): Response<String>

    companion object RetrofitClient {
        val webUsuario: WebUsuario by lazy {
            Retrofit.Builder()
                .baseUrl(AppConstantes.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(WebUsuario::class.java)
        }
    }
}
