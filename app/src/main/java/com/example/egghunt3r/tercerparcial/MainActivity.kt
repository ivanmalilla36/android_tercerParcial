package com.example.egghunt3r.tercerparcial

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import android.support.v7.app.AlertDialog
import kotlinx.android.synthetic.main.register_form.view.*


class MainActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener {
            var email = editEmail.text.toString()
            var password = editPassword.text.toString()
            if(email!=null && password!=null && email!="" && password!=""){
                mAuth!!.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in: success
                            // update UI for current User
                            val email = mAuth!!.getCurrentUser()
                            Toast.makeText(this,"Login Completo",Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, index::class.java)
                            startActivity(intent)
                        } else {
                            // Sign in: fail
                            //Log.e(TAG, "signIn: Fail!", task.exception)
                            Toast.makeText(this,"Usuario y/o contraseña incorrectos",Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{
                Toast.makeText(this,"Campos vacios",Toast.LENGTH_SHORT).show()
            }
        }

        btnRegistro.setOnClickListener {
            register()
        }

    }

    fun register(){
        val myDialog = layoutInflater.inflate(R.layout.register_form, null)
        val mBuilder = AlertDialog.Builder(this).setView(myDialog)
        val mAlertDialog = mBuilder.show()

        myDialog.btnCancelar.setOnClickListener {
            mAlertDialog.dismiss()
        }

        myDialog.btnRegistrar.setOnClickListener {
            var email = myDialog.editCorreo.text.toString()
            var password = myDialog.editPassword.text.toString()
            var cpassword = myDialog.editPassword2.text.toString()

            if(email!="" && password!="" && cpassword!="" && password.equals(cpassword) && password.length>=6 && cpassword.length>=6){
                createUser(email,password)
                mAlertDialog.dismiss()
            }else{
                Toast.makeText(this,"Las contrasenas no coinciden (deben ser de 6 caracteres minimo))",Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun createUser(email: String, password: String){
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in: success
                    // update UI for current User
                    val user = mAuth!!.currentUser
                    Toast.makeText(this,"Usuario creado",Toast.LENGTH_SHORT).show()
                } else {
                    // Sign in: fail
                    Toast.makeText(this, task.exception.toString(), Toast.LENGTH_LONG).show()
//                    Toast.makeText(this,"Error making the user",Toast.LENGTH_SHORT).show()
                }
            }
    }

}
