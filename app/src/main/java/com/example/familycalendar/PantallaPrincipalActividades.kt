package com.example.familycalendar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.familycalendar.adapter.ActividadesAdapter
import com.example.familycalendar.dataBase.SQLManager
import com.example.familycalendar.databinding.ActivityPantallaPrincipalActividadesBinding

class PantallaPrincipalActividades : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaPrincipalActividadesBinding
    private lateinit var ActividadesAdapter: ActividadesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaPrincipalActividadesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonAgregarActividad.setOnClickListener {
            val intent = Intent(this, PantallaCrearActividad::class.java)
            startActivity(intent)
        }
        ActividadesAdapter = ActividadesAdapter()
        binding.actividadesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ActividadesAdapter
        }

        mostrarActividades()
    }

    fun mostrarActividades() {
        val sqlManager = SQLManager(this)
        val actividades = sqlManager.getActividadesOrdenadasPorFecha(this)
        ActividadesAdapter.agregarActividades(actividades)
//        val actividad = DataClaseActividades(
//            titulo = "Almuerzo familiar",
//            fecha = "29/10/2023",
//            horaInicial = "12:00",
//            horaFinal = "16:30"
//        )
//
//        ActividadesAdapter.agregarActividades(listOf(actividad))
//
//        binding.actividadesRecyclerView.apply {
//            layoutManager =
//                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//            adapter = ActividadesAdapter
//        }
    }
}