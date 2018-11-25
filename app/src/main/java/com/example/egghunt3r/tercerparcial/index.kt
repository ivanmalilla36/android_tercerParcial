package com.example.egghunt3r.tercerparcial

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_index.*
import kotlinx.android.synthetic.main.metodo_pagos.view.*
import org.json.JSONException
import org.json.JSONObject

class index : AppCompatActivity() {

    internal var qrScanIntegrator: IntentIntegrator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        qrScanIntegrator = IntentIntegrator(this)

        qrScanIntegrator = IntentIntegrator(this)
        qrScanIntegrator?.setOrientationLocked(false)

        btnQr.setOnClickListener {
            qrScanIntegrator?.initiateScan()
        }

        btnVehiculos.setOnClickListener {

        }

        btnMetodos.setOnClickListener {
            val myDialog = layoutInflater.inflate(R.layout.metodo_pagos, null)
            val mBuilder = AlertDialog.Builder(this).setView(myDialog)
            val mAlertDialog = mBuilder.show()

            myDialog.btnCerrar.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            // If QRCode has no data.
            if (result.contents == null) {
                Toast.makeText(this,"el resultado es nulo", Toast.LENGTH_LONG).show()
            } else {
                // If QRCode contains data.
                try {
                    // Converting the data to json format
//                    val obj = JSONObject(result.contents)
//                    nombre.setText(obj.getString("nombre"))
//                    descripcion.setText(obj.getString("descripcion"))
//                    imagenURL.setText(obj.getString("imagenUrl"))
//                    latitud.setText(obj.getString("latitud"))
//                    longitud.setText(obj.getString("latitud"))
//                    telefono.setText(obj.getString("telefono"))

                } catch (e: JSONException) {
                    e.printStackTrace()

                    // Data not in the expected format. So, whole object as toast message.
                    Toast.makeText(this, result.contents, Toast.LENGTH_LONG).show()
                }

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_about -> {
            msgShow("Search")
            true
        }
        R.id.action_session -> {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    fun msgShow(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}