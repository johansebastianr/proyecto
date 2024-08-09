package com.example.proyecto.navegacion.ajustes

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto.R
import com.example.proyecto.databinding.ActivityBotonDialogoBinding

class BotonDialogo : AppCompatActivity() {
    private lateinit var binding: ActivityBotonDialogoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBotonDialogoBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
