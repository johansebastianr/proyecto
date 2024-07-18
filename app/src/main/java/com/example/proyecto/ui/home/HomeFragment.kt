package com.example.proyecto.ui.home

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentHomeBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class HomeFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var mMap: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        // Initialize map fragment
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Set focus change listener for the EditText textView50 (ubicación)
        binding.textView50.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.textView50.text = Editable.Factory.getInstance().newEditable("") // Vaciar el texto
            } else {
                if (binding.textView50.text.toString().isEmpty()) {
                    binding.textView50.text = Editable.Factory.getInstance().newEditable("Tu ubicación")
                }
            }
        }

        // Set focus change listener for the EditText textView51 (dirección)
        binding.textView51.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.textView51.text = Editable.Factory.getInstance().newEditable("") // Vaciar el texto
            } else {
                if (binding.textView51.text.toString().isEmpty()) {
                    binding.textView51.text = Editable.Factory.getInstance().newEditable("Calle 55 bn #8 a-27")
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Popayan and move the camera
        val popayan = LatLng(2.4448, -76.6147)
        val marker = MarkerOptions().position(popayan).title("Marker in Popayán")
        // Set default icon of Google Maps
        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        // Add marker
        mMap!!.addMarker(marker)
        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(popayan, 15f)) // Adjust zoom level as needed
    }
}
