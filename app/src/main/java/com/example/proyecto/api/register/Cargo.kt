package com.example.proyecto.api.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto.databinding.ActivityCargoBinding

class Cargo : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityCargoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate Called")
        binding = ActivityCargoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.conductor.setOnClickListener(this)
        binding.usuario.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.conductor -> {
                val intent = Intent(this, ConductorCrearCuenta::class.java).apply {
                }
                startActivity(intent)
                finish()
            }
            binding.usuario -> {
                val intent = Intent(this, UsuarioCrearCuenta::class.java).apply {
                }
                startActivity(intent)
                finish()
            }
        }
    }
}
