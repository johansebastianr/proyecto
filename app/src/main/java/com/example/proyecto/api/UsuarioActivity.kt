package com.example.proyecto.api

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.R
import com.example.proyecto.crud.EditarPerfil

class UsuarioActivity : AppCompatActivity(), UsuarioAdapter.OnItemClicked {

    private lateinit var recyclerView: RecyclerView
    private lateinit var usuarioAdapter: UsuarioAdapter
    private val listaUsuarios = ArrayList<Usuario>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        // Inicialización del RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        usuarioAdapter = UsuarioAdapter(this, listaUsuarios)

        // Configuración del RecyclerView
        recyclerView.adapter = usuarioAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Configuración del listener
        usuarioAdapter.setOnClick(this)

        // Cargar datos en el RecyclerView
        cargarDatos()
    }

    private fun cargarDatos() {
        // Agregar datos de ejemplo
        listaUsuarios.add(Usuario(1, "Juan", "123456789", "Dirección 1", "juan@example.com", "contraseña1"))
        listaUsuarios.add(Usuario(2, "Ana", "987654321", "Dirección 2", "ana@example.com", "contraseña2"))
        // Notificar al adaptador sobre los cambios en los datos
        usuarioAdapter.notifyDataSetChanged()
    }

    override fun editarUsuario(usuario: Usuario) {
        val intent = Intent(this, EditarPerfil::class.java)
        intent.putExtra("usuario", usuario)
        startActivity(intent)
    }

    override fun actualizarUsuario(usuario: Usuario) {
        // Implementa la lógica para actualizar el usuario
        // Por ejemplo, podrías mostrar un diálogo o una nueva actividad para editar el usuario
    }

    override fun borrarUsuario(idUsuario: Int) {
        // Implementa la lógica para borrar el usuario
        // Por ejemplo, podrías eliminar el usuario de la lista y notificar al adaptador
        listaUsuarios.removeAll { it.idUsuario == idUsuario }
        usuarioAdapter.notifyDataSetChanged()
    }
}
