package com.fabiomora.primerapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val dbHelper = UserDBHelper( this)

        val user = findViewById<EditText>(R.id.emailEditText)
        val pass = findViewById<EditText>(R.id.passwordEditText)
        val btnLogin = findViewById<Button>(R.id.loginButton)

        btnLogin.setOnClickListener(){
            val userString = user.text.toString().trim()
            val passString = pass.text.toString().trim()

            if(dbHelper.login(userString,passString)){
                Toast.makeText(this, "Bienvenido!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Usuario Incorrecto!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}