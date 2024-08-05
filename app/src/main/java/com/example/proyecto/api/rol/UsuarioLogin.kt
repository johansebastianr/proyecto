package com.example.proyecto.api.rol

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto.navegacion.home.Home
import com.example.proyecto.R
import com.example.proyecto.api.login.ApiService
import com.example.proyecto.api.login.LoginRequest
import com.example.proyecto.api.login.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsuarioLogin : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: TextView

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.0.17:3000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailEditText = findViewById(R.id.etCorreo)
        passwordEditText = findViewById(R.id.etContraseña)
        loginButton = findViewById(R.id.buttonIniciar)
        registerButton = findViewById(R.id.crearCuenta)

        loginButton.setOnClickListener {
            val correo = emailEditText.text.toString()
            val contraseña = passwordEditText.text.toString()

            if (correo.isNotEmpty() && contraseña.isNotEmpty()) {
                login(correo, contraseña)
            } else {
                Toast.makeText(this, "Por favor ingrese correo y contraseña", Toast.LENGTH_SHORT).show()
            }
        }

        registerButton.setOnClickListener {
            val intent = Intent(this, CrearCuenta::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun login(correo: String, contraseña: String) {
        val loginRequest = LoginRequest(correo, contraseña)

        apiService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val token = response.body()?.token
                    Toast.makeText(this@UsuarioLogin, "Bienvenido", Toast.LENGTH_LONG).show()

                    val intent = Intent(this@UsuarioLogin, Home::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@UsuarioLogin, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@UsuarioLogin, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
