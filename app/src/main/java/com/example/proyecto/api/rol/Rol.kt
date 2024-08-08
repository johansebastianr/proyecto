package com.example.proyecto.api.rol

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto.R
import com.example.proyecto.navegacion.animacion.SplashScreen
import com.example.proyecto.api.login.IniciarSesion
import com.example.proyecto.databinding.ActivityRolBinding

class Rol : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRolBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRolBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        checkSplashScreen()

        setOnClickListeners()
    }

    private fun checkSplashScreen() {
        val splashScreenShown = sharedPreferences.getBoolean("splash_screen_shown", false)
        if (!splashScreenShown) {
            Handler().postDelayed({
                startActivity(Intent(this, SplashScreen::class.java))
                finish()
            }, 3000)

            sharedPreferences.edit().putBoolean("splash_screen_shown", true).apply()
        }
    }

    private fun setOnClickListeners() {
        binding.conductor.setOnClickListener(this)
        binding.pasajero.setOnClickListener(this)
        binding.crearCuenta.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent: Intent? = when (v?.id) {
            R.id.conductor -> {
                sharedPreferences.edit().putString("selected_role", "conductor").apply()
                Intent(this, IniciarSesion::class.java)
            }
            R.id.pasajero -> {
                sharedPreferences.edit().putString("selected_role", "usuario").apply()
                Intent(this, IniciarSesion::class.java)
            }
            R.id.crearCuenta -> Intent(this, Cargo::class.java)
            else -> null
        }
        intent?.let {
            startActivity(it)
            finish()
        }
    }
}
