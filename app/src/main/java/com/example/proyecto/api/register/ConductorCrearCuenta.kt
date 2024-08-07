package com.example.proyecto.api.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyecto.api.conductor.ConductorClass
import com.example.proyecto.api.conductor.WebConductor
import com.example.proyecto.databinding.ActivityConductorCrearCuentaBinding
import kotlinx.coroutines.*

class ConductorCrearCuenta : AppCompatActivity() {

    private lateinit var binding: ActivityConductorCrearCuentaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConductorCrearCuentaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonEnviar.setOnClickListener {

            if (validarCampos()) {
                agregarConductor()
            } else {
                Toast.makeText(this, "Se deben llenar los campos", Toast.LENGTH_LONG).show()
            }
        }

        binding.sesion.setOnClickListener {
            val intent = Intent(this@ConductorCrearCuenta, Rol::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun validarCampos(): Boolean {
        return !(binding.tvNombre.text.isNullOrEmpty()
                || binding.tvTelefono.text.isNullOrEmpty()
                || binding.tvDireccion.text.isNullOrEmpty()
                || binding.tvCorreo.text.isNullOrEmpty()
                || binding.tvContraseA.text.isNullOrEmpty())
    }

    private fun agregarConductor() {
        val nuevoConductor = ConductorClass(
            -1,
            binding.tvNombre.text.toString(),
            binding.tvTelefono.text.toString(),
            binding.tvDireccion.text.toString(),
            binding.tvCorreo.text.toString(),
            binding.tvContraseA.text.toString()
        )

        CoroutineScope(Dispatchers.IO).launch {
            val response = WebConductor.RetrofitClient.webConductor.agregarConductor(nuevoConductor)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Toast.makeText(this@ConductorCrearCuenta, "Registro éxitoso", Toast.LENGTH_LONG).show()
                    limpiarCampos()
                    val intent = Intent(this@ConductorCrearCuenta, Rol::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@ConductorCrearCuenta, "Error el correo ya existe", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun limpiarCampos() {
        binding.tvNombre.setText("")
        binding.tvTelefono.setText("")
        binding.tvDireccion.setText("")
        binding.tvCorreo.setText("")
        binding.tvContraseA.setText("")
    }
}
