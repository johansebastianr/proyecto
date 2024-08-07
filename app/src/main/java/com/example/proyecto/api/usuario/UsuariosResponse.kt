package com.example.proyecto.api.usuario

import com.google.gson.annotations.SerializedName

data class UsuariosResponse(
    @SerializedName("listaUsuarios") var listaUsuarios: ArrayList<UsuarioClass>
)
