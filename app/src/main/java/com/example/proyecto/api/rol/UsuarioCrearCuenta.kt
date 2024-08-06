package com.example.proyecto.api.rol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyecto.api.RetrofitClient
import com.example.proyecto.api.Usuario
import com.example.proyecto.databinding.ActivityUsuarioCrearCuentaBinding
import kotlinx.coroutines.*

class UsuarioCrearCuenta : AppCompatActivity() {

    private lateinit var binding: ActivityUsuarioCrearCuentaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsuarioCrearCuentaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnEnviar.setOnClickListener {

            if (validarCampos()) {
                agregarUsuario()
            } else {
                Toast.makeText(this, "Se deben llenar los campos", Toast.LENGTH_LONG).show()
            }
        }

        binding.sesion.setOnClickListener {
            val intent = Intent(this@UsuarioCrearCuenta, Rol::class.java)
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

    private fun agregarUsuario() {
        val nuevoUsuario = Usuario(
            -1,
            binding.tvNombre.text.toString(),
            binding.tvTelefono.text.toString(),
            binding.tvDireccion.text.toString(),
            binding.tvCorreo.text.toString(),
            binding.tvContraseA.text.toString()
        )

        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitClient.webService.agregarUsuario(nuevoUsuario)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Toast.makeText(this@UsuarioCrearCuenta, "Registro éxitoso", Toast.LENGTH_LONG).show()
                    limpiarCampos()
                    val intent = Intent(this@UsuarioCrearCuenta, UsuarioLogin::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@UsuarioCrearCuenta, "Error el correo ya existe", Toast.LENGTH_LONG).show()
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
