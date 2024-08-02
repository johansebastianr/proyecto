package com.example.proyecto.api

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.R

class UsuarioAdapter(
    private val context: Context,
    private val listaUsuarios: ArrayList<Usuario>
) : RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>() {

    private var onClick: OnItemClicked? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.activity_editar_perfil, parent, false)
        return UsuarioViewHolder(vista)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = listaUsuarios[position]

        holder.etNombre.setText(usuario.nombre)
        holder.etEmail.setText(usuario.correo)

    }

    override fun getItemCount(): Int {
        return listaUsuarios.size
    }

    inner class UsuarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val etNombre: EditText = itemView.findViewById(R.id.tvNombre)
        val etEmail: EditText = itemView.findViewById(R.id.tvCorreo)

    }

    interface OnItemClicked {
        fun editarUsuario(usuario: Usuario)
        fun actualizarUsuario(usuario: Usuario)
        fun borrarUsuario(idUsuario: Int)
    }

    fun setOnClick(onClick: OnItemClicked?) {
        this.onClick = onClick
    }
}