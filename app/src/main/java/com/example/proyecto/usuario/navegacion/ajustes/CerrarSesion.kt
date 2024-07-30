package com.example.proyecto.usuario.navegacion.ajustes

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto.databinding.ActivityCerrarSesionBinding
import com.example.proyecto.usuario.MainActivity
import com.google.firebase.auth.FirebaseAuth

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
                val intent = Intent(this, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                startActivity(intent)
                finish()
            }

            binding.opcion2 -> {
                val intent = Intent(this, AjustesFragment::class.java)
                startActivity(intent)
            }
        }
    }

    private fun cerrarSesion() {
        FirebaseAuth.getInstance().signOut()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}