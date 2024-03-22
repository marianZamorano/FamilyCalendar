package com.example.familycalendar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.familycalendar.R
import com.example.familycalendar.dataBase.ActividadClass
import com.example.familycalendar.databinding.ItemActividadActividadesBinding
import kotlin.random.Random

class ActividadesAdapter(
    private val context: Context,
    private val actividades: List<ActividadClass>,
    private val listener: ActividadClickListener
) : RecyclerView.Adapter<ActividadesAdapter.ActividadesAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActividadesAdapterViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_actividad, parent, false)
        return ActividadesAdapterViewHolder(view, listener)
    }
    override fun onBindViewHolder(holder: ActividadesAdapterViewHolder, position: Int) {
        holder.bindView(actividades[position])
    }

    override fun getItemCount() = actividades.size

    class ActividadesAdapterViewHolder(
        itemView: View,
        private val listener: ActividadClickListener
    ) : RecyclerView.ViewHolder(itemView) {

        fun bindView(actividad: ActividadClass) {
            itemView.titulo_actividad.text = actividad.titulo
            itemView.fecha.text = actividad.fecha
            itemView.hora_inicial.text = actividad.horaInicial
            itemView.hora_final.text = actividad.horaFinal

            itemView.setOnClickListener {
                listener.onActividadClick(actividad)
            }
        }
    }

    interface ActividadClickListener {
        fun onActividadClick(actividad: ActividadClass)
    }
}