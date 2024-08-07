package com.example.proyecto.api

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.R

class UsuarioAdapter(
    private val context: Context,
    private val listaUsuarios: ArrayList<UsuarioClass>
) : RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>() {

    private var onClick: OnItemClicked? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_usuario, parent, false)
        return UsuarioViewHolder(vista)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = listaUsuarios[position]

        configureTextView(holder.tvNombre, usuario.nombre)
        configureTextView(holder.tvTelefono, usuario.telefono)
        configureTextView(holder.tvDireccion, usuario.direccion)
        configureTextView(holder.tvCorreo, usuario.correo)
        configureTextView(holder.tvContraseña, usuario.contraseña)

        holder.btnActualizar.setOnClickListener {
            onClick?.actualizarUsuario(usuario)
        }

        holder.btnAgregar.setOnClickListener {
            onClick?.editarUsuario(usuario)
        }

        holder.btnBorrar.setOnClickListener {
            onClick?.borrarUsuario(usuario.idUsuario)
        }
    }

    private fun configureTextView(textView: TextView, text: String) {
        textView.text = text
        textView.visibility = if (text.isEmpty()) View.GONE else View.VISIBLE
    }

    override fun getItemCount(): Int {
        return listaUsuarios.size
    }

    inner class UsuarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvTelefono: TextView = itemView.findViewById(R.id.tvTelefono)
        val tvDireccion: TextView = itemView.findViewById(R.id.tvDireccion)
        val tvCorreo: TextView = itemView.findViewById(R.id.tvCorreo)
        val tvContraseña: TextView = itemView.findViewById(R.id.tvContraseña)
        val btnActualizar: Button = itemView.findViewById(R.id.btnActualizar)
        val btnAgregar: Button = itemView.findViewById(R.id.btnAgregar)
        val btnBorrar: Button = itemView.findViewById(R.id.btnBorrar)
    }

    interface OnItemClicked {
        fun editarUsuario(usuario: UsuarioClass)
        fun actualizarUsuario(usuario: UsuarioClass)
        fun borrarUsuario(idUsuario: Int)
    }

    fun setOnClick(onClick: OnItemClicked?) {
        this.onClick = onClick
    }
}
