package com.example.proyecto.api.conductor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.R

class ConductorAdapter(
    private val context: Context,
    private val listaConductores: ArrayList<ConductorClass>
) : RecyclerView.Adapter<ConductorAdapter.ConductorViewHolder>() {

    private var onClick: OnItemClicked? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConductorViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_conductor, parent, false)
        return ConductorViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ConductorViewHolder, position: Int) {
        val conductor = listaConductores[position]

        configureTextView(holder.tvNombre, conductor.nombre)
        configureTextView(holder.tvTelefono, conductor.telefono)
        configureTextView(holder.tvDireccion, conductor.direccion)
        configureTextView(holder.tvCorreo, conductor.correo)
        configureTextView(holder.tvContraseña, conductor.contraseña)

        holder.btnActualizar.setOnClickListener {
            onClick?.actualizarConductor(conductor)
        }

        holder.btnAgregar.setOnClickListener {
            onClick?.editarConductor(conductor)
        }

        holder.btnBorrar.setOnClickListener {
            onClick?.borrarConductor(conductor.idConductor)
        }
    }

    private fun configureTextView(textView: TextView, text: String) {
        textView.text = text
        textView.visibility = if (text.isEmpty()) View.GONE else View.VISIBLE
    }

    override fun getItemCount(): Int {
        return listaConductores.size
    }

    inner class ConductorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
        fun editarConductor(usuario: ConductorClass)
        fun actualizarConductor(usuario: ConductorClass)
        fun borrarConductor(idUsuario: Int)
    }

    fun setOnClick(onClick: OnItemClicked?) {
        this.onClick = onClick
    }
}
