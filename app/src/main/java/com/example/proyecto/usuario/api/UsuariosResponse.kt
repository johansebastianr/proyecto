package com.example.proyecto.usuario.api

import com.google.gson.annotations.SerializedName

data class UsuariosResponse(
    @SerializedName("listaUsuarios") var listaUsuarios: ArrayList<Usuario>
)
