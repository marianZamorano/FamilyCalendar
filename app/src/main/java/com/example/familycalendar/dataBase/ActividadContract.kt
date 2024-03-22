package com.example.familycalendar.dataBase

object ActividadContract {
    const val DATABASE_NAME = "actividades.db"
    const val DATABASE_VERSION = 1

    object ActividadEntry {
        const val TABLE_NAME = "actividades"
        const val _ID = "_id"
        const val TITULO = "titulo"
        const val FECHA = "fecha"
        const val HORA_INICIAL = "hora_inicial"
        const val HORA_FINAL = "hora_final"
        const val DETALLE = "detalle"

        const val SQL_CREATE_ENTRIES =
            "CREATE TABLE $TABLE_NAME (" +
                    "$_ID INTEGER PRIMARY KEY," +
                    "$TITULO TEXT NOT NULL," +
                    "$FECHA TEXT NOT NULL," +
                    "$HORA_INICIAL TEXT NOT NULL," +
                    "$HORA_FINAL TEXT NOT NULL," +
                    "$DETALLE TEXT NOT NULL)"

        const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}