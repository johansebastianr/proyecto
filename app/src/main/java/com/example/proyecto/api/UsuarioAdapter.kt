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
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_usuario, parent, false)
        return UsuarioViewHolder(vista)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = listaUsuarios[position]

        holder.tvIdUsuario.text = usuario.idUsuario.toString()
        holder.etNombre.setText(usuario.nombre)
        holder.etEmail.setText(usuario.correo)

        holder.btnEditar.setOnClickListener {
            onClick?.editarUsuario(usuario)
        }

        holder.btnActualizar.setOnClickListener {
            onClick?.actualizarUsuario(usuario)
        }

        holder.btnEliminar.setOnClickListener {
            onClick?.borrarUsuario(usuario.idUsuario)
        }
    }

    override fun getItemCount(): Int {
        return listaUsuarios.size
    }

    inner class UsuarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvIdUsuario: TextView = itemView.findViewById(R.id.tvIdUsuario)
        val etNombre: EditText = itemView.findViewById(R.id.etNombre)
        val etEmail: EditText = itemView.findViewById(R.id.etEmail)

        val btnEditar: Button = itemView.findViewById(R.id.btnEditar)
        val btnActualizar: Button = itemView.findViewById(R.id.btnActualizar)
        val btnEliminar: Button = itemView.findViewById(R.id.btnEliminar)
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
