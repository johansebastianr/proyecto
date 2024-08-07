package com.example.proyecto.api.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto.R
import com.example.proyecto.navegacion.ajustes.CerrarSesion
import com.example.proyecto.navegacion.home.Conductor
import com.example.proyecto.navegacion.home.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IniciarSesion : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var forgetButton: TextView

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.12:3000/") // Asegúrate de que esta URL es correcta
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

        emailEditText = findViewById(R.id.etCorreo)
        passwordEditText = findViewById(R.id.etContraseña)
        loginButton = findViewById(R.id.buttonIniciar)
        forgetButton = findViewById(R.id.clave)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                login(email, password)
            } else {
                Toast.makeText(this, "Por favor ingrese correo y contraseña", Toast.LENGTH_SHORT).show()
            }
        }

        forgetButton.setOnClickListener {
            val intent = Intent(this, CerrarSesion::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun login(email: String, password: String) {
        val userType = "usuario"

        val loginRequest = LoginRequest(email, password, userType)

        apiService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    val token = loginResponse?.token
                    val receivedUserType = loginResponse?.tipo

                    if (token != null && receivedUserType != null) {
                        Toast.makeText(this@IniciarSesion, "Login exitoso", Toast.LENGTH_LONG).show()

                        val intent = when (receivedUserType) {
                            "usuario" -> Intent(this@IniciarSesion, Usuario::class.java)
                            "conductor" -> Intent(this@IniciarSesion, Conductor::class.java)
                            else -> {
                                Toast.makeText(this@IniciarSesion, "Tipo de usuario desconocido", Toast.LENGTH_SHORT).show()
                                return
                            }
                        }

                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@IniciarSesion, "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("IniciarSesion", "Error en la respuesta: ${response.code()} - ${response.message()}")
                    Toast.makeText(this@IniciarSesion, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("IniciarSesion", "Error de red: ${t.message}", t)
                Toast.makeText(this@IniciarSesion, "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
