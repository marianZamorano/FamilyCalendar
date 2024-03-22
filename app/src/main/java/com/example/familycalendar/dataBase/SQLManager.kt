package com.example.familycalendar.dataBase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.familycalendar.dataBase.ActividadContract
import java.sql.SQLException

class SQLManager(context: Context) : SQLiteOpenHelper(context, "sql.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE actividades (numero INT PRIMARY KEY, titulo TEXT, fecha DATE, horaInicial TEXT, horaFinal TEXT, detalle TEXT)")
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db == null) {
            return
        }

        try {
            db.execSQL(ActividadContract.SQL_DELETE_ENTRIES)
        } catch (e: SQLException) {
            print(e.message)
        }

        onCreate(db)
    }

    fun addActividad(actividad: ActividadClass): Long {
        val db = writableDatabase
        val contentValues = ContentValues()

        contentValues.put("titulo", actividad.titulo)
        contentValues.put("fecha", actividad.fecha)
        contentValues.put("hora_inicial", actividad.horaInicial)
        contentValues.put("hora_final", actividad.horaFinal)
        contentValues.put("detalle", actividad.detalle)

        return db.insert("actividades", null, contentValues)
    }

    fun getActividades(): Cursor {
        return readableDatabase.rawQuery("SELECT * FROM ${ActividadContract.TABLE_NAME} ORDER BY ${ActividadContract.COLUMN_NAME_FECHA}", null)
    }

    fun getActividadesOrdenadasPorFecha(): List<ActividadClass> {
        val actividadMapper = ActividadMapper()
        val actividades = mutableListOf<ActividadClass>()

        val db = readableDatabase
        val selectQuery = "SELECT * FROM ${ActividadContract.TABLE_NAME} ORDER BY ${ActividadContract.COLUMN_NAME_FECHA}"
        val cursor = db.rawQuery(selectQuery, null)

        actividades.addAll(actividadMapper.mapCursorToActividades(cursor))

        cursor.close()
        db.close()

        return actividades
    }
}