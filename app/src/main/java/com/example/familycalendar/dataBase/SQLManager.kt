package com.example.familycalendar.dataBase
import android.content.Context

class SQLManager(context: Context) {

    private val databaseManager = OperacionesDatabaseManager(context)

    fun addActividad(actividad: ActividadClass): Boolean {
        return databaseManager.addActividad(actividad)
    }

    fun getActividadesOrdenadasPorFecha(): ArrayList<ActividadClass> {
        return databaseManager.getActividadesOrdenadasPorFecha()
    }
}