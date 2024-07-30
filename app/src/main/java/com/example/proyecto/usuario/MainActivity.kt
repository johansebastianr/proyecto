package com.example.proyecto.usuario

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto.R
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var correo: EditText
    private lateinit var contraseña: EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        correo = findViewById(R.id.etCorreo)
        contraseña = findViewById(R.id.etContraseña)

        if (auth.currentUser != null) {
            startActivity(Intent(this@MainActivity, Home::class.java))
            finish()
        }

        findViewById<View>(R.id.buttonIniciar).setOnClickListener {
            val correo = correo.text.toString().trim()
            val contraseña = contraseña.text.toString().trim()

            if (correo.isEmpty()) {
                Toast.makeText(this, "Ingresa su correo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (contraseña.isEmpty()) {
                Toast.makeText(this, "Ingresa su contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(correo, contraseña)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@MainActivity, "¡Bienvenido!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@MainActivity, Home::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@MainActivity, "El correo no se encuentra registrado: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        findViewById<View>(R.id.crearCuenta).setOnClickListener {
            startActivity(Intent(this@MainActivity, CrearCuenta::class.java))
            finish()
        }
    }
}
