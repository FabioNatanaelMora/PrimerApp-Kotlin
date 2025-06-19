package com.fabiomora.primerapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDBHelper(context: Context): SQLiteOpenHelper(context, "ClubDB", null, 2) {

    //OnCreate
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
        CREATE TABLE usuarios(
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nombre TEXT UNIQUE,
            contrasenia TEXT
        )
        """.trimIndent())

        //Cargar un Usuario de prueba
        db.execSQL("INSERT INTO usuarios (nombre, contrasenia) VALUES ('admin', '1234')")

       db.execSQL("""
        CREATE TABLE socios(
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nombre TEXT UNIQUE,
            dni TEXT UNIQUE
            )
        """.trimIndent())
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

    fun insertarSocio(nombre: String, dni: String): Boolean {
        val db = writableDatabase
        val valores = ContentValues().apply {
            put("nombre", nombre)
            put("dni", dni)
        }

        val resultado = db.insert("socios", null, valores)

        return resultado != -1L
    }

    fun obtenerSocios():List<String>{
        val socios = mutableListOf<String>()             //para que se pueda ir agregando
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT nombre, dni FROM socios",null)
        if(cursor.moveToFirst()){
            do {
                val nombre = cursor.getString(0)
                val dni = cursor.getString(1)
                socios.add("$nombre - $dni")
            }while(cursor.moveToNext())  // siempre que halla un registro a traer lo va a repetir
        }
        cursor.close()
        return socios
    }

    fun eliminarSocioPorDni(dni: String){
        val db = writableDatabase
        db.delete("socios", "dni= ?", arrayOf(dni))
    }
    
}