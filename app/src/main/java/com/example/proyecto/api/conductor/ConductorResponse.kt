package com.example.proyecto.api.conductor

import com.google.gson.annotations.SerializedName

data class ConductorResponse(
    @SerializedName("listaConductores") var listaConductores: ArrayList<ConductorClass>
)
