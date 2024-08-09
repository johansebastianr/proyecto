package com.example.proyecto.api.conductor

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.R
import com.example.proyecto.api.usuario.UsuarioActivity

class ConductorActivity : AppCompatActivity(), ConductorAdapter.OnItemClicked {

    private lateinit var recyclerView: RecyclerView
    private lateinit var conductorAdapter: ConductorAdapter
    private val listaConductores = ArrayList<ConductorClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conductor)

        // Inicialización del RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        conductorAdapter = ConductorAdapter(this, listaConductores)

        // Configuración del RecyclerView
        recyclerView.adapter = conductorAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        conductorAdapter.setOnClick(this)

        // Cargar datos en el RecyclerView
        cargarDatos()
    }

    private fun cargarDatos() {
        // Agregar datos de ejemplo
        listaConductores.add(ConductorClass(1, "Juan", "123456789", "Dirección 1", "juan@example.com", "contraseña1"))
        listaConductores.add(ConductorClass(2, "Ana", "987654321", "Dirección 2", "ana@example.com", "contraseña2"))
        // Notificar al adaptador sobre los cambios en los datos
        conductorAdapter.notifyDataSetChanged()
    }

    override fun editarConductor(conductor: ConductorClass) {
        val intent = Intent(this, UsuarioActivity::class.java)
        intent.putExtra("conductor", conductor)
        startActivity(intent)
    }

    override fun actualizarConductor(conductor: ConductorClass) {
        // Implementa la lógica para actualizar el usuario
        // Por ejemplo, podrías mostrar un diálogo o una nueva actividad para editar el usuario
    }

    override fun borrarConductor(idConductor: Int) {
        // Implementa la lógica para borrar el usuario
        // Por ejemplo, podrías eliminar el usuario de la lista y notificar al adaptador
        listaConductores.removeAll { it.idConductor == idConductor }
        conductorAdapter.notifyDataSetChanged()
    }
}
