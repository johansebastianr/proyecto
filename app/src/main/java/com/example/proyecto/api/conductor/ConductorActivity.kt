package com.example.proyecto.api.conductor

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.lifecycleScope
import com.example.proyecto.R
import kotlinx.coroutines.launch

class ConductorActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var conductorAdapter: ConductorAdapter

    private val webConductor = WebConductor.RetrofitClient.webConductor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        obtenerConductores()
    }

    private fun obtenerConductores() {
        lifecycleScope.launch {
            try {
                val response = webConductor.obtenerConductores()
                if (response.isSuccessful) {
                    val conductores = response.body()?.listaUsuarios ?: emptyList()
                    conductorAdapter = ConductorAdapter(conductores)
                    recyclerView.adapter = conductorAdapter
                } else {
                    Toast.makeText(this@ConductorActivity, "Error al obtener usuarios: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@ConductorActivity, "Error desconocido: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
