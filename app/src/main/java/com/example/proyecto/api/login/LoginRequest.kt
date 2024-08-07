package com.example.proyecto.api.login

import android.os.Parcel
import android.os.Parcelable

data class LoginRequest(
    val correo: String,
    val contraseña: String,
    val tipo: String
) {

}
