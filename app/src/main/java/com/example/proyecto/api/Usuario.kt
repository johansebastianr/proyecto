package com.example.proyecto.api

import java.io.Serializable

data class Usuario(
    var idUsuario: Int,
    var nombre: String,
    var telefono: String,
    var direccion: String,
    var correo: String,
    var contraseña: String
) : Serializable
