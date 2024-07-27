package com.example.proyecto.ui.ajustes

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto.R
import com.example.proyecto.databinding.ActivityBotonDialogoBinding
import com.example.proyecto.page.Debito
import com.example.proyecto.page.Nequi

class BotonDialogo : AppCompatActivity() {
    private lateinit var binding: ActivityBotonDialogoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBotonDialogoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton: RadioButton = findViewById(checkedId)

            val intent: Intent = when (checkedId) {
                R.id.radioButton -> Intent(this, Nequi::class.java)
                R.id.radioButton2 -> Intent(this, Debito::class.java)
                else -> throw IllegalArgumentException("Unknown RadioButton clicked")
            }

            startActivity(intent)
        }
    }
}
