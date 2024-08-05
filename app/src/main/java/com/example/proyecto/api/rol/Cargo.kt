package com.example.proyecto.api.rol

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto.databinding.ActivityCargoBinding

class Cargo : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityCargoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate Called")
        binding = ActivityCargoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button6.setOnClickListener(this)
        binding.button7.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.button6 -> {
                val intent = Intent(this, CrearCuenta::class.java).apply {
                }
                startActivity(intent)
                finish()
            }
            binding.button7 -> {
                val intent = Intent(this, CrearCuenta::class.java).apply {
                }
                startActivity(intent)
                finish()
            }
        }
    }
}