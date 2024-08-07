package com.example.proyecto.api.login

import android.os.Parcel
import android.os.Parcelable

data class LoginResponse(
    val token: String,
    val tipo: String
) {

}
