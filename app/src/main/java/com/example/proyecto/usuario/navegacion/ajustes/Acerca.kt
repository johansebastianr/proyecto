package com.example.proyecto.usuario.navegacion.ajustes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto.databinding.ActivityAcercaBinding

class Acerca: AppCompatActivity() {
    private lateinit var binding: ActivityAcercaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAcercaBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}