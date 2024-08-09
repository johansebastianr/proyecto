package com.example.proyecto.navegacion.animacion

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto.R
import com.example.proyecto.api.register.Rol
import com.example.proyecto.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE)

        val splashScreenShown = sharedPreferences.getBoolean("splash_screen_shown", false)

        val animacion1 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo)
        val animacion2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba)


        val imageViewLogo = findViewById<ImageView>(R.id.imageViewLogo)

        imageViewLogo.startAnimation(animacion2)


        Handler().postDelayed({
            val intent = Intent(this@SplashScreen, Rol::class.java)
            startActivity(intent)
            finish()
        }, 3000)

        sharedPreferences.edit().putBoolean("splash_screen_shown", true).apply()
    }
}
