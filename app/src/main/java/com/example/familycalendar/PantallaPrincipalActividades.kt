package com.example.familycalendar

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.familycalendar.adapter.ActividadesAdapter
import com.example.familycalendar.dataBase.ActividadClass
import com.example.familycalendar.dataBase.SQLManager
import com.example.familycalendar.databinding.ActivityPantallaPrincipalActividadesBinding

class PantallaPrincipalActividades : AppCompatActivity(), AgregarActividadClickListener {

    private lateinit var binding: ActivityPantallaPrincipalActividadesBinding
    private lateinit var actividadesAdapter: ActividadesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaPrincipalActividadesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonAgregarActividad.setOnClickListener {
            onAgregarActividadClicked()
        }

        actividadesAdapter.setOnItemClickListener(object : ActividadesAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val actividadSeleccionada = actividadesAdapter.getActividadAt(position)
                mostrarDialogoEliminarActividad(actividadSeleccionada)
            }
        })

        binding.actividadesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = actividadesAdapter
        }

        mostrarActividades()
    }

    override fun onAgregarActividadClicked() {
        val intent = Intent(this, PantallaCrearActividad::class.java)
        startActivity(intent)
    }

    fun mostrarActividades() {
        val sqlManager = SQLManager(this)
        val actividades = sqlManager.getActividadesOrdenadasPorFecha()
        actividadesAdapter.agregarActividades(actividades)
    }

    private fun mostrarDialogoEliminarActividad(actividad: ActividadClass) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar actividad")
            .setMessage("¿Estás seguro de que quieres eliminar esta actividad?")
            .setPositiveButton("Sí") { _, _ ->
                eliminarActividad(actividad)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun eliminarActividad(actividad: ActividadClass) {
        val sqlManager = SQLManager(this)
        val eliminada = sqlManager.eliminarActividad(actividad)

        if (eliminada) {
            Toast.makeText(this, "Actividad eliminada", Toast.LENGTH_SHORT).show()
            mostrarActividades()
        } else {
            Toast.makeText(this, "Error al eliminar la actividad", Toast.LENGTH_SHORT).show()
        }
    }
}