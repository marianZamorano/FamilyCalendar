package com.example.familycalendar.dataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLManager (context: Context):SQLiteOpenHelper(context, "sql.db", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE actividades (numero INT PRIMARY KEY, titulo VARCHAR, fecha DATE, horaInicial TIME, horaFinal TIME, detalle VARCHAR)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun addActividad(context: Context, datos: ActividadClass):Boolean{
        var response = true
        var contentValues = ContentValues()
        contentValues.put("numero", datos.numero)
        contentValues.put("titulo", datos.titulo)
        contentValues.put("detalle", datos.detalle)
        contentValues.put("fecha", datos.fecha)
        contentValues.put("hora inicial", datos.horaInicial)
        contentValues.put("hora final", datos.horaFinal)
        var db = SQLManager(context)
        var manager = db.writableDatabase
        try {
            manager.insert("actividades", null, contentValues)
        }
        catch (e: Exception){
            print(e.message)
            response = false
        }
        finally {
            db.close()
        }
        return response
    }
}