package com.example.myapplication.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentCallsBinding
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_calls.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CallsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CallsFragment : Fragment(), GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener {
     var lat: String? = null
    var long: String? = null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var googleApiClient: GoogleApiClient
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        buildGoogleApiClient()
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentCallsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_calls, container, false)
        binding.listen = this
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CallsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CallsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun call(view: View) {

        /// you can make alert dialog by make xml layout and inflate it with alert dialog builder
        /// val view = LayoutInflater.from(activity).inflate(R.layout.x,null , false)
        /// alertBuilder.setView(view)


        val alertBuilder = AlertDialog.Builder(activity)

        alertBuilder.setTitle("Call")
        alertBuilder.setMessage("Do you want to make Call ")
        alertBuilder.setPositiveButton("Call") { _, _ ->
            Toast.makeText(requireActivity(), "Calling ..", Toast.LENGTH_LONG).show()
        }
        alertBuilder.setNegativeButton("Cancel") { _, _ ->

        }
        val alertDialog = alertBuilder.create()
        alertDialog.show()
    }

    fun save(view: View) {
        val permissionName = android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        val canWrite: Boolean?

        val permission = ActivityCompat.checkSelfPermission(requireActivity(), permissionName)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(permissionName), 100)
        }
        canWrite = true
        if (canWrite){
            writeStorage()
        }

    }

    private fun writeStorage() {
        try {
            val folder: File? = requireActivity().getExternalFilesDir("folder")
            val myFile = File(folder, "notes.txt")
            val editor = FileOutputStream(myFile)
            editor.write(phoneEditText.text.toString().toByteArray())
            editor.close()
            phoneEditText.text.clear()
            Toast.makeText(requireActivity(), "Saved ", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(requireActivity(), "$e", Toast.LENGTH_LONG).show()
        }
    }

    fun read(view: View) {
        try {
            val folder: File? = requireActivity().getExternalFilesDir("folder")
            val myFile = File(folder, "notes.txt")
            val editor = FileInputStream(myFile)
            editor.bufferedReader().forEachLine {
                val s = StringBuilder()
                s.append(it)
                phoneEditText.setText(s)
            }
            Toast.makeText(requireActivity(), "Data read ", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(requireActivity(), "$e", Toast.LENGTH_LONG).show()
        }
    }



    fun getMyLocation(view: View) {

        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val permissionName = android.Manifest.permission.ACCESS_FINE_LOCATION
            requestPermissions(arrayOf(permissionName),25)
            return
        }
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            lat = it.latitude.toString()
            long = it.longitude.toString()
            showLocationAlertDialog(lat!!, long!!)
        }
    }

    fun saveToFirebase(view: View){
       val phone = phoneEditText.text.toString()
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Message")
//        myRef.child("Message").setValue(phone)
//        Toast.makeText(requireActivity(),"done",Toast.LENGTH_LONG).show()

        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val  a = snapshot.getValue(String::class.java)
                phoneEditText.setText(a)
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })


    }
    private  fun showLocationAlertDialog(lat:String,long:String){
        val alertBuilder = AlertDialog.Builder(activity)
        alertBuilder.setTitle("Location")
        alertBuilder.setMessage("Your Location is $lat , $long  ")

        val alertDialog = alertBuilder.create()
        alertDialog.show()
    }
    override fun onStart() {
        super.onStart()
        googleApiClient.connect()
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults.isNotEmpty()){
            when(requestCode){
                25 ->
                    if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
                            lat = it.latitude.toString()
                            long = it.longitude.toString()
                            showLocationAlertDialog(lat!!, long!!)
                        }

                    }
            }
        }
    }

    override fun onConnected(p0: Bundle?) {

    }

    override fun onConnectionSuspended(p0: Int) {
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
    }

   private fun buildGoogleApiClient(){
        googleApiClient = GoogleApiClient.Builder(requireActivity())
            .addApi(LocationServices.API).addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .build()
    }

    override fun onDestroy() {
        super.onDestroy()
        googleApiClient.disconnect()
    }
}