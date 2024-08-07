package com.example.proyecto.api.conductor

import java.io.Serializable

data class ConductorClass(
    var idConductor: Int,
    var nombre: String,
    var telefono: String,
    var direccion: String,
    var correo: String,
    var contraseña: String
) : Serializable
