package com.example.familycalendar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.familycalendar.R
import com.example.familycalendar.dataBase.ActividadClass
import com.example.familycalendar.databinding.ItemActividadActividadesBinding
import kotlin.random.Random

class ActividadesAdapter : RecyclerView.Adapter<ActividadesAdapter.ActividadesAdapterViewHolder>() {

    private var context: Context? = null
    private var listaActividades = mutableListOf<ActividadClass>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActividadesAdapterViewHolder {
        context = parent.context
        val binding = ItemActividadActividadesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ActividadesAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ActividadesAdapterViewHolder,
        position: Int
    ) {
        val actividad = listaActividades[position]
        holder.binding.tituloActividad.text = actividad.titulo
        holder.binding.detalleActividad.text = actividad.detalle
        holder.binding.fecha.text = actividad.fecha
        holder.binding.horaInicial.text = actividad.horaInicial
        holder.binding.horaFinal.text = actividad.horaFinal
        holder.binding.fondoActividadActividades.setBackgroundColor(
            holder.itemView.resources.getColor(
                getRandomColor(),
                null
            )
        )
    }

    private fun getRandomColor(): Int {
        val colorCode = mutableListOf<Int>()
        colorCode.add(R.color.lilaclaro)
        colorCode.add(R.color.verdeTurquesaClaro)
        colorCode.add(R.color.celesteGrisMedio)
        colorCode.add(R.color.lightSkyBlue)
        colorCode.add(R.color.celesteGrisClaro)
        val numero = Random.nextInt(colorCode.size)
        return colorCode.get(numero)

    }

    override fun getItemCount(): Int = listaActividades.size

    inner class ActividadesAdapterViewHolder(val binding: ItemActividadActividadesBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun agregarActividades(newDataActividades: List<ActividadClass>) {
        listaActividades.clear()
        listaActividades.addAll(newDataActividades)
        notifyDataSetChanged()
    }
}