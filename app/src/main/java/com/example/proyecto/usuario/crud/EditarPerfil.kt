package com.example.proyecto.usuario.crud


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto.databinding.ActivityEditarPerfilBinding
import com.example.proyecto.usuario.navegacion.ajustes.AjustesFragment

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
                val intent = Intent(this, AjustesFragment::class.java).apply {
                }
                startActivity(intent)
                finish()
            }
        }
    }
}