package com.example.proyecto.navegacion.ajustes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.proyecto.R
import com.example.proyecto.api.usuario.UsuarioActivity
import com.example.proyecto.databinding.FragmentAjustesBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class AjustesFragment : Fragment() {

    private var _binding: FragmentAjustesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAjustesBinding.inflate(inflater, container, false)


        binding.textView16.setOnClickListener {
            val intent = Intent(requireContext(), Acerca::class.java)
            startActivity(intent)
        }


        binding.textView4.setOnClickListener {
            val intent = Intent(requireContext(), CerrarSesion::class.java)
            startActivity(intent)

        }

        binding.textView6.setOnClickListener {
            val intent = Intent(requireContext(), UsuarioActivity::class.java)
            startActivity(intent)
        }

        binding.imageView3.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(requireContext())
            val bottomSheetView = layoutInflater.inflate(R.layout.activity_boton_dialogo, null)
            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()
        }

        return binding.root
    }

    private fun finish() {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}