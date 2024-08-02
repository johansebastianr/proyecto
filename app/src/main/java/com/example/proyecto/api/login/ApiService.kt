package com.example.proyecto.api.login

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(val correo: String, val contraseña: String)
data class LoginResponse(val token: String)

interface ApiService {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}
