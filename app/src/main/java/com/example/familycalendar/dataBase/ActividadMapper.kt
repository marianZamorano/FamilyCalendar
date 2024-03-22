package com.example.familycalendar.dataBase

import android.database.Cursor

class ActividadMapper {
    fun mapCursorToActividades(cursor: Cursor): List<ActividadClass> {
        val actividades = mutableListOf<ActividadClass>()

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val titulo = cursor.getString(cursor.getColumnIndex("titulo"))
                val fecha = cursor.getString(cursor.getColumnIndex("fecha"))
                val horaInicial = cursor.getString(cursor.getColumnIndex("hora_inicial"))
                val horaFinal = cursor.getString(cursor.getColumnIndex("hora_final"))
                val detalle = cursor.getString(cursor.getColumnIndex("detalle"))

                actividades.add(ActividadClass(titulo, fecha, horaInicial, horaFinal, detalle))
            } while (cursor.moveToNext())
        }

        return actividades
    }
}