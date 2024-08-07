package com.example.proyecto.api.crud

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.proyecto.R
import com.example.proyecto.api.RetrofitClient
import com.example.proyecto.api.UsuarioClass
import kotlinx.coroutines.launch
import retrofit2.HttpException

class EditarPerfil : AppCompatActivity() {

    private lateinit var tvNombre: EditText
    private lateinit var tvTelefono: EditText
    private lateinit var tvDireccion: EditText
    private lateinit var tvCorreo: EditText
    private lateinit var tvContraseña: EditText
    private lateinit var btnActualizar: Button
    private lateinit var btnAgregar: Button
    private lateinit var btnBorrar: Button

    private val webService = RetrofitClient.webService
    private var idUsuario: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_perfil)

        // Inicializar vistas
        tvNombre = findViewById(R.id.tvNombre)
        tvTelefono = findViewById(R.id.tvTelefono)
        tvDireccion = findViewById(R.id.tvDireccion)
        tvCorreo = findViewById(R.id.tvCorreo)
        tvContraseña = findViewById(R.id.tvContraseña)
        btnActualizar = findViewById(R.id.btnActualizar)
        btnAgregar = findViewById(R.id.btnAgregar)
        btnBorrar = findViewById(R.id.btnBorrar)

        // Obtener el ID del usuario del Intent
        idUsuario = intent.getIntExtra("USER_ID", -1)

        if (idUsuario == -1) {
            // Configurar para agregar un nuevo usuario
            btnActualizar.visibility = View.GONE
            btnBorrar.visibility = View.GONE
            btnAgregar.visibility = View.VISIBLE
        } else {
            // Configurar para editar un usuario existente
            btnActualizar.visibility = View.VISIBLE
            btnBorrar.visibility = View.VISIBLE
            btnAgregar.visibility = View.GONE
            obtenerUsuario(idUsuario)
        }

        btnActualizar.setOnClickListener { onActualizarClick() }
        btnAgregar.setOnClickListener { onAgregarClick() }
        btnBorrar.setOnClickListener { onBorrarClick() }
    }

    private fun obtenerUsuario(idUsuario: Int) {
        lifecycleScope.launch {
            try {
                val response = webService.obtenerUsuario(idUsuario)
                if (response.isSuccessful) {
                    val usuario = response.body()
                    if (usuario != null) {
                        // Cargar los datos en los campos
                        tvNombre.setText(usuario.nombre)
                        tvTelefono.setText(usuario.telefono)
                        tvDireccion.setText(usuario.direccion)
                        tvCorreo.setText(usuario.correo)
                        tvContraseña.setText(usuario.contraseña)
                    } else {
                        Toast.makeText(this@EditarPerfil, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@EditarPerfil, "Error al obtener datos del usuario", Toast.LENGTH_SHORT).show()
                }
            } catch (e: HttpException) {
                Toast.makeText(this@EditarPerfil, "Error en la conexión", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this@EditarPerfil, "Error desconocido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onActualizarClick() {
        val nombre = tvNombre.text.toString().trim()
        val telefono = tvTelefono.text.toString().trim()
        val direccion = tvDireccion.text.toString().trim()
        val correo = tvCorreo.text.toString().trim()
        val contraseña = tvContraseña.text.toString().trim()

        if (nombre.isNotEmpty() && telefono.isNotEmpty() && direccion.isNotEmpty() && correo.isNotEmpty() && contraseña.isNotEmpty()) {
            lifecycleScope.launch {
                try {
                    val usuario = UsuarioClass(idUsuario, nombre, telefono, direccion, correo, contraseña)
                    val response = webService.actualizarUsuario(idUsuario, usuario)
                    if (response.isSuccessful) {
                        Toast.makeText(this@EditarPerfil, "Perfil actualizado correctamente", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@EditarPerfil, "Error al actualizar el perfil: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: HttpException) {
                    Toast.makeText(this@EditarPerfil, "Error en la conexión", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(this@EditarPerfil, "Error desconocido", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onAgregarClick() {
        val nombre = tvNombre.text.toString().trim()
        val telefono = tvTelefono.text.toString().trim()
        val direccion = tvDireccion.text.toString().trim()
        val correo = tvCorreo.text.toString().trim()
        val contraseña = tvContraseña.text.toString().trim()

        if (nombre.isNotEmpty() && telefono.isNotEmpty() && direccion.isNotEmpty() && correo.isNotEmpty() && contraseña.isNotEmpty()) {
            lifecycleScope.launch {
                try {
                    val usuario = UsuarioClass(0, nombre, telefono, direccion, correo, contraseña)
                    val response = webService.agregarUsuario(usuario)
                    if (response.isSuccessful) {
                        Toast.makeText(this@EditarPerfil, "Usuario agregado correctamente", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@EditarPerfil, "Error al agregar el usuario: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: HttpException) {
                    Toast.makeText(this@EditarPerfil, "Error en la conexión", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(this@EditarPerfil, "Error desconocido", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onBorrarClick() {
        lifecycleScope.launch {
            try {
                val response = webService.borrarUsuario(idUsuario)
                if (response.isSuccessful) {
                    Toast.makeText(this@EditarPerfil, "Usuario borrado correctamente", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@EditarPerfil, "Error al borrar el usuario: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: HttpException) {
                Toast.makeText(this@EditarPerfil, "Error en la conexión", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this@EditarPerfil, "Error desconocido", Toast.LENGTH_SHORT).show()
            }
        }
    }
}