package com.drivertest.donatenowapp.ui.Hospital.fragmentscreens

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.drivertest.donatenowapp.BuildConfig
import com.drivertest.donatenowapp.R
import com.google.android.gms.location.*


import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient


import com.google.android.gms.maps.model.BitmapDescriptorFactory

import com.google.android.gms.maps.model.Marker
import android.graphics.Bitmap
import android.graphics.Canvas

import android.graphics.drawable.Drawable
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.drivertest.donatenowapp.databinding.FragmentMapsBinding

import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.CameraUpdate
import java.lang.Exception


class MapsFragment : Fragment() {

    private var map: GoogleMap? = null
    private var cameraPosition: CameraPosition? = null

    // The entry point to the Places API.
    private lateinit var placesClient: PlacesClient

    // The entry point to the Fused Location Provider.
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private val defaultLocation = LatLng(-33.8523341, 151.2106085)
    private var locationPermissionGranted = false
    private lateinit var locationManager: LocationManager
    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private var lastKnownLocation: Location? = null
    private var likelyPlaceNames: Array<String?> = arrayOfNulls(0)
    private var likelyPlaceAddresses: Array<String?> = arrayOfNulls(0)
    private var likelyPlaceAttributions: Array<List<*>?> = arrayOfNulls(0)
    private var likelyPlaceLatLngs: Array<LatLng?> = arrayOfNulls(0)


    companion object {
        val PERMISSION_ID = 42
        private const val TAG = "mloc"
        private const val DEFAULT_ZOOM = 15
        private const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1

        // Keys for storing activity state.
        private const val KEY_CAMERA_POSITION = "camera_position"
        private const val KEY_LOCATION = "location"

        // Used for selecting the current place.
        private const val M_MAX_ENTRIES = 5
    }
    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        //getLocationPermission()

        // Turn on the My Location layer and the related control on the map.
       // updateLocationUI()

        // Get the current location of the device and set the position of the map.
    //    getDeviceLocation()
 //       findUserLocation()

//        val sydney = LatLng(-34.0, 151.0)
//        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        map = googleMap
        getLastLocation(googleMap)
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation(googleMap:GoogleMap) {
        if (checkPermissions()) {
            if (isLocationEnabled()) {

                fusedLocationProviderClient.lastLocation.addOnCompleteListener(requireActivity()) { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {

                        val m1 = googleMap.addMarker(
                            MarkerOptions()
                                .position(LatLng(38.609556, -1.139637))
                                .anchor(0.5f, 0.5f)
                                .title("Title1")
                                .snippet("Snippet1")
                                .icon(BitmapFromVector(requireContext(),R.drawable.ic_donatenow))
                        )


                        val m2 = googleMap.addMarker(
                            MarkerOptions()
                                .position(LatLng(38.709556, -1.139637))
                                .anchor(0.5f, 0.5f)
                                .title("Title2")
                                .snippet("Snippet2")
                                .icon(BitmapFromVector(requireContext(),R.drawable.ic_donatenow))
                        )

                        val m3 = googleMap.addMarker(
                            MarkerOptions()
                                .position(LatLng(38.809556, -1.139637))
                                .anchor(0.5f, 0.5f)
                                .title("Title3")
                                .snippet("Snippet3")
                                .icon(BitmapFromVector(requireContext(),R.drawable.ic_donatenow))
                        )


                        var mar = mutableListOf(m1,m2,m3)

                        val builder = LatLngBounds.Builder()
                        for (marker in mar) {
                            builder.include(marker.position)
                        }
                        val bounds = builder.build()
                        val padding = 0 // offset from edges of the map in pixels

                        val cu = CameraUpdateFactory.newLatLngBounds(bounds, padding)
                        googleMap.moveCamera(cu);
                        googleMap.animateCamera(cu);
//                        val latLng = LatLng(location.latitude, location.longitude)
//                        val markerOptions = MarkerOptions().anchor(0.5f, 0.5f).position(latLng).title("I am here!")
//                        val markerOptionsNew = MarkerOptions().anchor(0.5f, 0.5f).position(LatLng(location.latitude+3.20,location.latitude-3.20)).title("I am there2!").icon(BitmapFromVector(requireContext(),R.drawable.ic_donatenow))
//                        val markerOptionsNew3 = MarkerOptions().anchor(0.5f, 0.5f).position(LatLng(location.latitude+3.20,location.latitude-3.20)).title("I am there3!").icon(BitmapFromVector(requireContext(),R.drawable.ic_donatenow))
//                        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
//                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5f))
//                        googleMap.addMarker(markerOptions)
//                        googleMap.addMarker(markerOptionsNew)
//                        googleMap.addMarker(markerOptionsNew3)

//                        val radiusInKM:Double = 0.1;
//                        val bearing:Double = 0.1;
//                        val destinationPoint:LatLng? = getDestinationPoint( latLng, bearing, radiusInKM);
//if(destinationPoint!=null) {
//    googleMap.animateCamera(CameraUpdateFactory.newLatLng(destinationPoint))
//    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(destinationPoint, 5f))
//    googleMap.addMarker(markerOptionsNew)
//    googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(
//        LatLng(destinationPoint!!.latitude,
//            destinationPoint!!.longitude), DEFAULT_ZOOM.toFloat()))
//}
//                        findViewById<TextView>(R.id.latTextView).text = location.latitude.toString()
//                        findViewById<TextView>(R.id.lonTextView).text = location.longitude.toString()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }





    private fun BitmapFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        // below line is use to generate a drawable.
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)

        // below line is use to set bounds to our vector drawable.
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )

        // below line is use to create a bitmap for our
        // drawable which we have added.
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )

        // below line is use to add bitmap in our canvas.
        val canvas = Canvas(bitmap)

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas)

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
    private fun getDestinationPoint(source: LatLng, brng: Double, dist: Double): LatLng? {
        var brng = brng
        var dist = dist
        dist = dist / 6371
        brng = Math.toRadians(brng)
        val lat1 = Math.toRadians(source.latitude)
        val lon1 = Math.toRadians(source.longitude)
        val lat2 = Math.asin(
            Math.sin(lat1) * Math.cos(dist) +
                    Math.cos(lat1) * Math.sin(dist) * Math.cos(brng)
        )
        val lon2 = lon1 + Math.atan2(
            Math.sin(brng) * Math.sin(dist) *
                    Math.cos(lat1),
            Math.cos(dist) - Math.sin(lat1) *
                    Math.sin(lat2)
        )
        if(lat2 is Double && lon2 is Double){
            return LatLng(Math.toDegrees(lat2), Math.toDegrees(lon2))
        }
        else{return null}

//        return if (!(lat2 is Double) || !lon2 is Double) {
//            Unit
//        } else LatLng(Math.toDegrees(lat2), Math.toDegrees(lon2))
    }



    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
        fusedLocationProviderClient!!.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var mLastLocation: Location = locationResult.lastLocation
            Log.d("+++++++++locationresult",""+mLastLocation.latitude.toString());
            Log.d("+++++++++LocationResult",""+mLastLocation.longitude.toString());
            val m1 = map?.addMarker(
                MarkerOptions()
                    .position(LatLng(38.609556, -1.139637))
                    .anchor(0.7f, 0.7f)
                    .title("Title1")
                    .snippet("Snippet1")
                    .icon(BitmapFromVector(requireContext(),R.drawable.ic_donatenow))
            )


            val m2 = map?.addMarker(
                MarkerOptions()
                    .position(LatLng(40.4272414, -3.7020037))
                    .anchor(0.5f, 0.5f)
                    .title("Title2")
                    .snippet("Snippet2")
                    .icon(BitmapFromVector(requireContext(),R.drawable.ic_donatenow))
            )

            val m3 = map?.addMarker(
                MarkerOptions()
                    .position(LatLng(43.2568193, -2.9225534))
                    .anchor(0.3f, 0.3f)
                    .title("Title3")
                    .snippet("Snippet3")
                    .icon(BitmapFromVector(requireContext(),R.drawable.ic_donatenow))
            )
//                                        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(
//                                LatLng(mLastLocation!!.latitude,
//                                    mLastLocation!!.longitude), DEFAULT_ZOOM.toFloat()))
//            findViewById<TextView>(R.id.latTextView).text = mLastLocation.latitude.toString()
//            findViewById<TextView>(R.id.lonTextView).text = mLastLocation.longitude.toString()
        }
    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                map?.let { getLastLocation(it) }
            }
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        map?.let { map ->
            outState.putParcelable(KEY_CAMERA_POSITION, map.cameraPosition)
            outState.putParcelable(KEY_LOCATION, lastKnownLocation)
        }
        super.onSaveInstanceState(outState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (savedInstanceState != null) {
            lastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION)
            cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION)
        }

        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Construct a PlacesClient
        Places.initialize(requireActivity().applicationContext, BuildConfig.MAPS_API_KEY)
        placesClient = Places.createClient(requireActivity())

        // Construct a FusedLocationProviderClient.
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        val sendRequest = view.findViewById<Button>(R.id.sendRequest) as Button
        sendRequest.setOnClickListener { val alertDialog: AlertDialog = showDialog()
            // Set other dialog properties
            alertDialog.setCancelable(true)
            alertDialog.show() }
    }


    fun showDialog(): AlertDialog {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Alert!!!")
       builder.setMessage("SEND REQUEST TO THE DONORS ?")
        builder.setPositiveButton("Accept", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface, which: Int) {

                try {

                } catch (e: Exception) {
                    e.printStackTrace()
                }
                dialog.dismiss();

            }
        })
        builder.setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface, which: Int) {
                dialog.dismiss()
            }
        })
        return builder.create()
    }
//    private fun getLocation() {
//        locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
////        if ((context?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.ACCESS_FINE_LOCATION) } != PackageManager.PERMISSION_GRANTED)) {
////            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
////        }
//        if (ActivityCompat.checkSelfPermission(requireContext(),
//                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//            && ActivityCompat.checkSelfPermission(requireContext(),
//                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
//        ) {
//            getLocationPermission()
//        }
//        else {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5f,requireActivity())
//            //  locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this) }
//        }
//    }
//    override fun onLocationChanged(location: Location) {
////        tvGpsLocation = findViewById(R.id.textView)
////        tvGpsLocation.text = "Latitude: " + location.latitude + " , Longitude: " + location.longitude
//    }

//    private fun findUserLocation() {
//
//        if (ActivityCompat.checkSelfPermission(requireContext(),
//                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//            && ActivityCompat.checkSelfPermission(requireContext(),
//                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
//        ) {
//           getLocationPermission()
//        }
//        else {
//            val locationRequest = LocationRequest.create() // Create location request.
//            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY // Set priority.
//
//            val locationCallback: LocationCallback = object : LocationCallback() {
//                override fun onLocationResult(locationResult: LocationResult) {
//                    for (location in locationResult.locations) {
//                        if (location != null) {
//
//                            // TODO: Show your code here.
//                            // Such as:
//                            val lat = location.latitude
//                            val lon = location.longitude
//                            map?.moveCamera(CameraUpdateFactory.newLatLngZoom(
//                                LatLng(lastKnownLocation!!.latitude,
//                                    lastKnownLocation!!.longitude), DEFAULT_ZOOM.toFloat()))
//                        }
//                    }
//                }
//            }
//
//            // Create a location provider client and send request for getting location.
//            val client = LocationServices.getFusedLocationProviderClient(requireContext())
//            if (ActivityCompat.checkSelfPermission(requireContext(),
//                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(requireContext(),
//                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
//            ) {
//                getLocationPermission()
//            }
//            else{
//            client.requestLocationUpdates(locationRequest, locationCallback, null)}
//        }
//    }

//    @SuppressLint("MissingPermission")
//    private fun getDeviceLocation() {
//        /*
//         * Get the best and most recent location of the device, which may be null in rare
//         * cases when a location is not available.
//         */
//        try {
//            if (locationPermissionGranted) {
//                val locationResult = fusedLocationProviderClient.lastLocation
//                locationResult.addOnCompleteListener(requireActivity()) { task ->
//                    if (task.isSuccessful) {
//                        // Set the map's camera position to the current location of the device.
//                        lastKnownLocation = task.result
//                   //     Log.d("+++++++++++++", ""+lastKnownLocation.latitude.toString())
//                   //     Log.d("+++++++++++++", ""+lastKnownLocation.longitude.toString())
//                        if (lastKnownLocation != null) {
//                            map?.moveCamera(CameraUpdateFactory.newLatLngZoom(
//                                LatLng(lastKnownLocation!!.latitude,
//                                    lastKnownLocation!!.longitude), DEFAULT_ZOOM.toFloat()))
//                        }
//                    } else {
////                        Log.d(TAG, "Current location is null. Using defaults.")
////                        Log.e(TAG, "Exception: %s", task.exception)
//                        map?.moveCamera(CameraUpdateFactory
//                            .newLatLngZoom(defaultLocation, DEFAULT_ZOOM.toFloat()))
//                        map?.uiSettings?.isMyLocationButtonEnabled = false
//                    }
//                }
//            }
//        } catch (e: SecurityException) {
//            Log.e("Exception: %s", e.message, e)
//        }
//    }
//
//
//
//    @SuppressLint("MissingPermission")
//    private fun showCurrentPlace() {
//        if (map == null) {
//            return
//        }
//        if (locationPermissionGranted) {
//            // Use fields to define the data types to return.
//            val placeFields = listOf(Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG)
//
//            // Use the builder to create a FindCurrentPlaceRequest.
//            val request = FindCurrentPlaceRequest.newInstance(placeFields)
//
//            // Get the likely places - that is, the businesses and other points of interest that
//            // are the best match for the device's current location.
//            val placeResult = placesClient.findCurrentPlace(request)
//            placeResult.addOnCompleteListener { task ->
//                if (task.isSuccessful && task.result != null) {
//                    val likelyPlaces = task.result
//
//                    // Set the count, handling cases where less than 5 entries are returned.
//                    val count = if (likelyPlaces != null && likelyPlaces.placeLikelihoods.size < M_MAX_ENTRIES) {
//                        likelyPlaces.placeLikelihoods.size
//                    } else {
//                        M_MAX_ENTRIES
//                    }
//                    var i = 0
//                    likelyPlaceNames = arrayOfNulls(count)
//                    likelyPlaceAddresses = arrayOfNulls(count)
//                    likelyPlaceAttributions = arrayOfNulls<List<*>?>(count)
//                    likelyPlaceLatLngs = arrayOfNulls(count)
//                    for (placeLikelihood in likelyPlaces?.placeLikelihoods ?: emptyList()) {
//                        // Build a list of likely places to show the user.
//                        likelyPlaceNames[i] = placeLikelihood.place.name
//                        likelyPlaceAddresses[i] = placeLikelihood.place.address
//                        likelyPlaceAttributions[i] = placeLikelihood.place.attributions
//                        likelyPlaceLatLngs[i] = placeLikelihood.place.latLng
//                        i++
//                        if (i > count - 1) {
//                            break
//                        }
//                    }
//
//                    // Show a dialog offering the user the list of likely places, and add a
//                    // marker at the selected place.
//               //     openPlacesDialog()
//                } else {
//                    Log.e(TAG, "Exception: %s", task.exception)
//                }
//            }
//        } else {
//            // The user has not granted permission.
//                getLocationPermission()
//            Log.i(TAG, "The user did not grant location permission.")
//
//            // Add a default marker, because the user hasn't selected a place.
////            map?.addMarker(MarkerOptions()
////                .title("PossibleLocation")
////                .position(defaultLocation)
////                .snippet("Loc")
//
//            // Prompt the user for permission.
//
//        }
//    }
//
//
//    /**
//     * Prompts the user for permission to use the device location.
//     */
//    private fun getLocationPermission() {
//        /*
//         * Request location permission, so that we can get the location of the
//         * device. The result of the permission request is handled by a callback,
//         * onRequestPermissionsResult.
//         */
//        if (ContextCompat.checkSelfPermission(requireContext().applicationContext,
//                Manifest.permission.ACCESS_FINE_LOCATION)
//            == PackageManager.PERMISSION_GRANTED) {
//            locationPermissionGranted = true
//        } else {
//            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)
//        }
//    }
//
//    /**
//     * Handles the result of the request for location permissions.
//     */
//    override fun onRequestPermissionsResult(requestCode: Int,
//                                            permissions: Array<String>,
//                                            grantResults: IntArray) {
//        locationPermissionGranted = false
//        when (requestCode) {
//            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
//
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.isNotEmpty() &&
//                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    locationPermissionGranted = true
//                    getDeviceLocation()
//                }
//            }
//        }
//      //  updateLocationUI()
//    }
//
//
//    private fun updateLocationUI() {
//        if (map == null) {
//            return
//        }
//        try {
//            if (locationPermissionGranted) {
//                map?.isMyLocationEnabled = true
//                map?.uiSettings?.isMyLocationButtonEnabled = true
//            } else {
//                map?.isMyLocationEnabled = false
//                map?.uiSettings?.isMyLocationButtonEnabled = false
//                lastKnownLocation = null
//                getLocationPermission()
//            }
//        } catch (e: SecurityException) {
//            Log.e("Exception: %s", e.message, e)
//        }
//    }
}