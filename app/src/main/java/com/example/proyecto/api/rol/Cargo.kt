package com.example.proyecto.api.rol

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto.R
import com.example.proyecto.api.login.IniciarSesion

class Cargo : AppCompatActivity() {

    private lateinit var conductorButton: Button
    private lateinit var usuarioButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cargo)

        conductorButton = findViewById(R.id.conductor)
        usuarioButton = findViewById(R.id.usuario)

        conductorButton.setOnClickListener {
            RolAdapter.userType = "conductor"
            navigateToLogin()
        }

        usuarioButton.setOnClickListener {
            RolAdapter.userType = "usuario"
            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, IniciarSesion::class.java)
        startActivity(intent)
        finish()
    }
}
