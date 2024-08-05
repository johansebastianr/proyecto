package com.example.proyecto.navegacion.mensajes

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto.navegacion.home.Home
import com.example.proyecto.databinding.ActivityMensajesBinding

class Mensajes : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMensajesBinding

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityMensajesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Establecer OnClickListener para los botones
        binding.textView30.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {

            binding.textView30 -> {
                val intent = Intent(this, Home::class.java).apply {
                }
                startActivity(intent)
                finish()
            }
        }
    }
}
