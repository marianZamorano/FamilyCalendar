package com.example.familycalendar.fragment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

class PantallaPrincipalActividades : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaPrincipalActividadesBinding
    private val ActividadesAdapter by lazy { ActividadesAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaPrincipalActividadesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.botonIrPantallaPrincipalActividades.setOnClickListener {
            val intent = Intent(this, PantallaPrincipal::class.java)
            startActivity(intent)
        }
        binding.botonAgregarActividad.setOnClickListener {
            val intent = Intent(this, PantallaCrearActividad::class.java)
            startActivity(intent)
        }

        mostrarActividades()
    }

    fun mostrarActividades() {
        val actividad = DataClaseActividades(
            titulo = "Almuerzo familiar",
            fecha = "29/10/2023",
            horaInicial = "12:00",
            horaFinal = "16:30"
        )

        ActividadesAdapter.agregarActividades(listOf(actividad))

        binding.actividadesRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ActividadesAdapter
        }
    }

}