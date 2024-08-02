package com.example.proyecto.crud

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto.CrearCuenta
import com.example.proyecto.databinding.ActivityEditarPerfilBinding

class EditarPerfil : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityEditarPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonVolver.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.buttonVolver -> {
                val intent = Intent(this, CrearCuenta::class.java).apply {
                }
                startActivity(intent)
                finish()
            }
        }
    }
}