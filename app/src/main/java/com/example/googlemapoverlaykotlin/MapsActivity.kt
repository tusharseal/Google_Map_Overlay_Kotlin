package com.example.googlemapoverlaykotlin

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.googlemapoverlaykotlin.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.GroundOverlayOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val mudLatLng = LatLng(28.762139, 77.505818)
        val markerOptions = MarkerOptions().position(mudLatLng).title("Muradagar")
        mMap.addMarker(markerOptions)

        mMap.moveCamera(CameraUpdateFactory.newLatLng(mudLatLng))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mudLatLng, 16f))

        //Circle
        mMap.addCircle(
            CircleOptions().center(mudLatLng).radius(1000.0).fillColor(Color.GREEN).strokeColor(
                Color.DKGRAY
            )
        )

        //Polygon
        mMap.addPolygon(
            PolygonOptions().add(
                LatLng(28.762139, 77.505818),
                LatLng(28.762139, 79.505818),
                LatLng(30.762139, 79.505818),
                LatLng(30.762139, 77.505818),
                LatLng(28.762139, 77.505818)
            ).fillColor(Color.YELLOW).strokeColor(Color.BLUE)
        )

        //GroundOverlay
        mMap.addGroundOverlay(
            GroundOverlayOptions()
                .position(mudLatLng, 1000f, 1000f)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.getyoteam))
                .clickable(true)
        )
    }
}