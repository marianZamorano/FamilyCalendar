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
    private lateinit var actividadesAdapter: ActividadesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaPrincipalActividadesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonAgregarActividad.setOnClickListener {
            onAgregarActividadClicked()
        }

        actividadesAdapter = ActividadesAdapter()
        binding.actividadesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = actividadesAdapter
        }

        mostrarActividades()
    }

    fun onAgregarActividadClicked() {
        val intent = Intent(this, PantallaCrearActividad::class.java)
        startActivity(intent)
    }

    fun mostrarActividades() {
        val sqlManager = SQLManager(this)
        val actividades = sqlManager.getActividadesOrdenadasPorFecha()
        actividadesAdapter.agregarActividades(actividades)
    }
}