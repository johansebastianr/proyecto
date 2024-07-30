package com.example.proyecto.usuario.api

data class Usuario(
    var idUsuario: Int,
    var nombre: String,
    var telefono: String,
    var direccion: String,
    var correo: String,
    var contraseña: String
)
