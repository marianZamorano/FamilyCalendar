package com.example.familycalendar.dataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLManager(context: Context) : SQLiteOpenHelper(context, "sql.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE actividades (numero INT PRIMARY KEY, titulo TEXT, fecha DATE, horaInicial TEXT, horaFinal TEXT, detalle TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun addActividad(context: Context, datos: ActividadClass): Boolean {
        var response = true
        var contentValues = ContentValues()
        contentValues.put("titulo", datos.titulo)
        contentValues.put("fecha", datos.fecha)
        contentValues.put("horaInicial", datos.horaInicial)
        contentValues.put("horafinal", datos.horaFinal)
        contentValues.put("detalle", datos.detalle)
        var db = SQLManager(context)
        var manager = db.writableDatabase
        try {
            manager.insert("actividades", null, contentValues)
        } catch (e: Exception) {
            print(e.message)
            response = false
        } finally {
            db.close()
        }
        return response
    }

    fun getActividadesOrdenadasPorFecha(context: Context): List<ActividadClass> {
        val actividades = mutableListOf<ActividadClass>()
        val db = SQLManager(context).readableDatabase
        val cursor = db.rawQuery("SELECT * FROM actividades ORDER BY fecha", null)

        // Obtener los índices de columna una vez
        val tituloIndex = cursor.getColumnIndex("titulo")
        val fechaIndex = cursor.getColumnIndex("fecha")
        val horaInicialIndex = cursor.getColumnIndex("horaInicial")
        val horaFinalIndex = cursor.getColumnIndex("horaFinal")
        val detalleIndex = cursor.getColumnIndex("detalle")

        if (cursor.moveToFirst()) {
            do {
                val titulo = cursor.getString(tituloIndex)
                val fecha = cursor.getString(fechaIndex)
                val horaInicial = cursor.getString(horaInicialIndex)
                val horaFinal = cursor.getString(horaFinalIndex)
                val detalle = cursor.getString(detalleIndex)

                // Agregar la actividad a la lista
                actividades.add(ActividadClass(titulo, fecha, horaInicial, horaFinal, detalle))
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return actividades
    }
}