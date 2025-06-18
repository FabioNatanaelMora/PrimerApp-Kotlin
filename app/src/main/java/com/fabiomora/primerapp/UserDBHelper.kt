package com.fabiomora.primerapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDBHelper(context: Context): SQLiteOpenHelper(context, "UsuarioDB", null, 1) {

    //OnCreate
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
        CREATE TABLE usuarios(
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nombre TEXT UNIQUE,
            contrasenia TEXT
        )
    """.trimIndent())

        db.execSQL("INSERT INTO usuarios (nombre, contrasenia) VALUES ('admin', '1234')")
    }

    //onUpgrade
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Elimina la tabla vieja y la vuelve a crear
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        onCreate(db)
    }
    fun login( nombre: String, contrasenia: String): Boolean{
        var db = readableDatabase
        var cursor = db.rawQuery(
            "SELECT * FROM usuarios WHERE nombre =? AND contrasenia=?",
            arrayOf(nombre,contrasenia)
        )

        var existe = cursor.count > 0
        return existe
    }
    
}