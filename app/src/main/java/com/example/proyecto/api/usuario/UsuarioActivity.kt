package com.example.proyecto.api.usuario

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.lifecycleScope
import com.example.proyecto.R
import kotlinx.coroutines.launch

class UsuarioActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var usuarioAdapter: UsuarioAdapter

    private val webUsuario = WebUsuario.RetrofitClient.webUsuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        obtenerUsuarios()
    }

    private fun obtenerUsuarios() {
        lifecycleScope.launch {
            try {
                val response = webUsuario.obtenerUsuarios()
                if (response.isSuccessful) {
                    val usuarios = response.body()?.listaUsuarios ?: emptyList()
                    usuarioAdapter = UsuarioAdapter(usuarios)
                    recyclerView.adapter = usuarioAdapter
                } else {
                    Toast.makeText(this@UsuarioActivity, "Error al obtener usuarios: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@UsuarioActivity, "Error desconocido: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
