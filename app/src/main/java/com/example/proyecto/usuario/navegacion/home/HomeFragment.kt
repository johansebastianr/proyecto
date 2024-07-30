package com.example.proyecto.usuario.navegacion.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentHomeBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.Locale

class HomeFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var mMap: GoogleMap? = null

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val locationPermissionRequestCode = 1001
    private lateinit var geocoder: Geocoder

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult.locations?.forEach { location ->
                updateLocationOnMap(location)
            }
        }
    }

    private val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000)
        .setMinUpdateIntervalMillis(5000)
        .build()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        geocoder = Geocoder(requireContext(), Locale.getDefault())

        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.textView50.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.textView50.text = Editable.Factory.getInstance().newEditable("")
            } else {
                if (binding.textView50.text.toString().isEmpty()) {
                    binding.textView50.text = Editable.Factory.getInstance().newEditable("Tu ubicación")
                }
            }
        }

        binding.textView51.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.textView51.text = Editable.Factory.getInstance().newEditable("")
            } else {
                if (binding.textView51.text.toString().isEmpty()) {

                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        if (::fusedLocationClient.isInitialized) {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            if (isGPSEnabled()) {
                startLocationUpdates()
            } else {
                Log.e("HomeFragment", "GPS is not enabled")
                binding.textView50.text = Editable.Factory.getInstance().newEditable("GPS no está activado")
            }
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionRequestCode)
        }
    }

    private fun isGPSEnabled(): Boolean {
        val locationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as android.location.LocationManager
        return locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionRequestCode)
        }
    }

    private fun updateLocationOnMap(location: Location) {
        val userLatLng = LatLng(location.latitude, location.longitude)
        val userMarker = MarkerOptions().position(userLatLng).title("Tu Ubicación")
        userMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        mMap?.clear()
        mMap?.addMarker(userMarker)
        mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 15f))

        val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
        if (addresses != null && addresses.isNotEmpty()) {
            val address = addresses[0].getAddressLine(0)
            binding.textView51.text = Editable.Factory.getInstance().newEditable(address)
        } else {
            binding.textView51.text = Editable.Factory.getInstance().newEditable("Dirección no disponible")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionRequestCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (isGPSEnabled()) {
                    startLocationUpdates()
                } else {
                    Log.e("HomeFragment", "GPS is not enabled")
                    binding.textView50.text =
                        Editable.Factory.getInstance().newEditable("GPS no está activado")
                }
            } else {
                Log.e("HomeFragment", "Location permission denied")
                binding.textView50.text =
                    Editable.Factory.getInstance().newEditable("Permiso de ubicación denegado")
            }
        }
    }
}
