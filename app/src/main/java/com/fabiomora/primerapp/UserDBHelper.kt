package com.fabiomora.primerapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDBHelper(context: Context): SQLiteOpenHelper(context, "UsuarioDB", null, 1) {

    //OnCreate
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
          CREATE TABLE usuario(
          id INTEGER PRIMARY KEY AUTOINCREMENT,
          nombre TEXT UNIQUE,
          contrasenia TEXT
          )  
        """.trimIndent()) //elimina las tres comilla sin espacio

        db.execSQL("INSERT INTO usuarios (nombre, contrasenia) VALUES ('admin', '1234')")
    }

    //onUpgrade
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}