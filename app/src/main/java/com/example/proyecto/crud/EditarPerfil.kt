package com.example.proyecto.crud

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto.MainActivity
import com.example.proyecto.R
import com.example.proyecto.databinding.ActivityEditarPerfilBinding
import com.example.proyecto.ui.ajustes.AjustesFragment

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