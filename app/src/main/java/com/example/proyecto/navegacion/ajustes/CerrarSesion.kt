package com.example.proyecto.navegacion.ajustes

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto.api.register.Rol
import com.example.proyecto.databinding.ActivityCerrarSesionBinding

class CerrarSesion : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityCerrarSesionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCerrarSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.opcion1.setOnClickListener(this)
        binding.opcion2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.opcion1 -> {
                cerrarSesion()
                val intent = Intent(this, Rol::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                startActivity(intent)
                finish()
            }

            binding.opcion2 -> {
                val intent = Intent(this, UsuarioAjustesFragment::class.java)
                startActivity(intent)
            }
        }
    }

    private fun cerrarSesion() {

        val intent = Intent(this, Rol::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}