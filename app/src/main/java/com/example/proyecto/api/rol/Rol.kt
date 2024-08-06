package com.example.proyecto.api.rol

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto.SplashScreen
import com.example.proyecto.databinding.ActivityRolBinding

class Rol : AppCompatActivity() {
    private lateinit var binding: ActivityRolBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRolBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)

        val splashScreenShown = sharedPreferences.getBoolean("splash_screen_shown", false)

        if (!splashScreenShown) {
            Handler().postDelayed({
                val intent = Intent(this@Rol, SplashScreen::class.java)
                startActivity(intent)
                finish()
            }, 3000)

            sharedPreferences.edit().putBoolean("splash_screen_shown", true).apply()
        }

        binding.conductor.setOnClickListener {
            val intent = Intent(this@Rol, ConductorCrearCuenta::class.java)
            startActivity(intent)
            finish()
        }

        binding.pasajero.setOnClickListener {
            val intent = Intent(this@Rol, UsuarioCrearCuenta::class.java)
            startActivity(intent)
            finish()
        }

        binding.crearCuenta.setOnClickListener {
            val intent = Intent(this@Rol, UsuarioCrearCuenta::class.java)
            startActivity(intent)
            finish()
        }

    }
}
