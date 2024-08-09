package com.example.proyecto.api.conductor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.R

class ConductorAdapter(private val conductores: List<ConductorClass>) :
    RecyclerView.Adapter<ConductorAdapter.ConductorViewHolder>() {

    class ConductorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvTelefono: TextView = itemView.findViewById(R.id.tvTelefono)
        val tvDireccion: TextView = itemView.findViewById(R.id.tvDireccion)
        val tvCorreo: TextView = itemView.findViewById(R.id.tvCorreo)
        val tvContraseña: TextView = itemView.findViewById(R.id.tvContraseña)
    }

    // Inflar el diseño del ítem y crear el ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConductorViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_usuario, parent, false)
        return ConductorViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ConductorViewHolder, position: Int) {
        val usuario = conductores[position]
        holder.tvNombre.text = usuario.nombre
        holder.tvTelefono.text = usuario.telefono
        holder.tvDireccion.text = usuario.direccion
        holder.tvCorreo.text = usuario.correo
        holder.tvContraseña.text = usuario.contraseña
    }

    override fun getItemCount(): Int {
        return conductores.size
    }
}
