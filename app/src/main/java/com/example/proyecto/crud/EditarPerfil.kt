package com.example.proyecto.crud

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto.R

class EditarPerfil : AppCompatActivity() {

    private lateinit var tvNombre: EditText
    private lateinit var tvTelefono: EditText
    private lateinit var tvDireccion: EditText
    private lateinit var tvCorreo: EditText
    private lateinit var tvContraseña: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_perfil)

        // Inicializar vistas
        tvNombre = findViewById(R.id.tvNombre)
        tvTelefono = findViewById(R.id.tvTelefono)
        tvDireccion = findViewById(R.id.tvDireccion)
        tvCorreo = findViewById(R.id.tvCorreo)
        tvContraseña = findViewById(R.id.tvContraseña)

        // Obtener datos del usuario y establecer en las vistas
        val usuario = obtenerDatosUsuario()
        tvNombre.setText(usuario.nombre)
        tvTelefono.setText(usuario.telefono)
        tvDireccion.setText(usuario.direccion)
        tvCorreo.setText(usuario.correo)
        tvContraseña.setText(usuario.contraseña)
    }

    fun onActualizarClick(view: View) {
        val nombre = tvNombre.text.toString()
        val telefono = tvTelefono.text.toString()
        val direccion = tvDireccion.text.toString()
        val correo = tvCorreo.text.toString()
        val contraseña = tvContraseña.text.toString()

        if (nombre.isNotEmpty() && telefono.isNotEmpty() && direccion.isNotEmpty() && correo.isNotEmpty() && contraseña.isNotEmpty()) {
            val exito = actualizarDatosUsuario(nombre, telefono, direccion, correo, contraseña)

            if (exito) {
                Toast.makeText(this, "Perfil actualizado correctamente", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error al actualizar el perfil", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun obtenerDatosUsuario(): Usuario {
        // Simulación de obtención de datos del usuario
        return Usuario("Juan Pérez", "555-1234", "Calle Falsa 123", "juan.perez@example.com", "juan123")
    }

    private fun actualizarDatosUsuario(nombre: String, telefono: String, direccion: String, correo: String, contraseña: String): Boolean {
        // Simulación de actualización de datos del usuario
        return true
    }
}

data class Usuario(val nombre: String, val telefono: String, val direccion: String, val correo: String, val contraseña: String)
