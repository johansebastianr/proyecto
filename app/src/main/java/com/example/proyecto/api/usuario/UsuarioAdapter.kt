package com.example.proyecto.api.usuario

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.R

class UsuarioAdapter(private val usuarios: List<UsuarioClass>) :
    RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>() {

    class UsuarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvTelefono: TextView = itemView.findViewById(R.id.tvTelefono)
        val tvDireccion: TextView = itemView.findViewById(R.id.tvDireccion)
        val tvCorreo: TextView = itemView.findViewById(R.id.tvCorreo)
        val tvContraseña: TextView = itemView.findViewById(R.id.tvContraseña)
    }

    // Inflar el diseño del ítem y crear el ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_usuario, parent, false)
        return UsuarioViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = usuarios[position]
        holder.tvNombre.text = usuario.nombre
        holder.tvTelefono.text = usuario.telefono
        holder.tvDireccion.text = usuario.direccion
        holder.tvCorreo.text = usuario.correo
        holder.tvContraseña.text = usuario.contraseña
    }

    override fun getItemCount(): Int {
        return usuarios.size
    }
}
