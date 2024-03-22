package com.example.familycalendar.dataBase
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class OperacionesDatabaseManager(context: Context) {

    private val dbHelper: DatabaseHelper = DatabaseHelper(context)

    fun addActividad(actividad: ActividadClass): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITULO, actividad.titulo)
            put(COLUMN_DETALLES, actividad.detalle)
            put(COLUMN_FECHA, actividad.fecha)
            put(COLUMN_HORA_INICIAL, actividad.horaInicial)
            put(COLUMN_HORA_FINAL, actividad.horaFinal)
        }

        val newRowId = db.insert(TABLE_ACTIVIDAD, null, values)
        return newRowId != -1L
    }

    fun getActividadesOrdenadasPorFecha(): ArrayList<ActividadClass> {
        val actividades = ArrayList<ActividadClass>()
        val db = dbHelper.readableDatabase
        val sortOrder = "$COLUMN_FECHA DESC"

        val cursor: Cursor = db.query(
            TABLE_ACTIVIDAD,
            null,
            null,
            null,
            null,
            null,
            sortOrder
        )

        while (cursor.moveToNext()) {
            val titulo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITULO))
            val detalles = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DETALLES))
            val fecha = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FECHA))
            val horaInicial = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HORA_INICIAL))
            val horaFinal = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HORA_FINAL))

            val actividad = ActividadClass(titulo, detalles, fecha, horaInicial, horaFinal)
            actividades.add(actividad)
        }
        cursor.close()
        return actividades
    }

    fun eliminarActividad(actividad: ActividadClass): Boolean {
        val db = dbHelper.writableDatabase
        val whereClause = "$COLUMN_TITULO = ? AND $COLUMN_FECHA = ?"
        val whereArgs = arrayOf(actividad.titulo, actividad.fecha)
        val success = db.delete(TABLE_ACTIVIDAD, whereClause, whereArgs) > 0
        db.close()
        return success
    }

    // DatabaseHelper inner class
    private class DatabaseHelper(context: Context) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        override fun onCreate(db: SQLiteDatabase) {
            val SQL_CREATE_ACTIVIDAD_TABLE = """
                CREATE TABLE $TABLE_ACTIVIDAD (
                    $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    $COLUMN_TITULO TEXT NOT NULL,
                    $COLUMN_DETALLES TEXT,
                    $COLUMN_FECHA TEXT NOT NULL,
                    $COLUMN_HORA_INICIAL TEXT NOT NULL,
                    $COLUMN_HORA_FINAL TEXT NOT NULL
                );
            """.trimIndent()

            db.execSQL(SQL_CREATE_ACTIVIDAD_TABLE)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_ACTIVIDAD")
            onCreate(db)
        }
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "FamilyCalendar.db"

        // Table name and column names
        private const val TABLE_ACTIVIDAD = "actividades"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_TITULO = "titulo"
        private const val COLUMN_DETALLES = "detalles"
        private const val COLUMN_FECHA = "fecha"
        private const val COLUMN_HORA_INICIAL = "hora_inicial"
        private const val COLUMN_HORA_FINAL = "hora_final"
    }
}