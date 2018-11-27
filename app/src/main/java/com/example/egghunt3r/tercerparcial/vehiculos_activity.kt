package com.example.egghunt3r.tercerparcial

import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import kotlinx.android.synthetic.main.activity_vehiculos_activity.*

class vehiculos_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehiculos_activity)

        val url = "https://dry-anchorage-31131.herokuapp.com/api/vehiculos"

        vehiculosList.layoutManager = LinearLayoutManager(this)

        fun isNetworkConnected(): Boolean {
            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager //1
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }

        if (isNetworkConnected()){
            doAsync {
                //doAsync tuvimos que importar anko
                val result = Request(url).run()
                uiThread { longToast("https://dry-anchorage-31131.herokuapp.com/api/vehiculos Request performed")
                    vehiculosList.adapter = VehiculoAdapter(result, this@vehiculos_activity)
                }
            }
            AlertDialog.Builder(this).setTitle("Connection settled down")
                .setMessage("connected to internet")
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }
        else{
            Toast.makeText(this,"sin conexion a internet",Toast.LENGTH_LONG).show()
        }
    }
    }

