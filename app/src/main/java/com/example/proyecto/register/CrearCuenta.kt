package com.example.proyecto.register


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto.MainActivity
import com.example.proyecto.databinding.ActivityCrearCuentaBinding

class CrearCuenta : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityCrearCuentaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearCuentaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sesion.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.sesion -> {
                val intent = Intent(this, MainActivity::class.java).apply {
                }
                startActivity(intent)
            }
        }
    }
}
