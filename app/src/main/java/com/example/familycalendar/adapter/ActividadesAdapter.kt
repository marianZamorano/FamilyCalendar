package com.example.familycalendar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.familycalendar.R
import com.example.familycalendar.dataBase.ActividadClass
import com.example.familycalendar.databinding.ItemActividadActividadesBinding
import kotlin.random.Random

class ActividadesAdapter : RecyclerView.Adapter<ActividadesAdapter.ActividadesAdapterViewHolder>() {

    private var context: Context? = null
    private var listaActividades = mutableListOf<ActividadClass>()
    private var itemClickListener: AdapterView.OnItemClickListener? = null
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): ActividadesAdapterViewHolder {
//        context = parent.context
//        return ActividadesAdapterViewHolder(
//            ItemActividadActividadesBinding.inflate(
//                LayoutInflater.from(parent.context),
//                parent,
//                false
//            )
//        )
//    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

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
        holder.binding.fecha.text = actividad.fecha
        holder.binding.horaInicial.text = actividad.horaInicial
        holder.binding.horaFinal.text = actividad.horaFinal
        holder.binding.fondoActividadActividades.setBackgroundColor(
            holder.itemView.resources.getColor(
                getRandomColor(),
                null
            )
        )

        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(position)
        }
    }

//    private fun getRandomColor(): Int {
//        val colorCode = mutableListOf<Int>()
//        colorCode.add(R.color.lilaclaro)
//        colorCode.add(R.color.verdeTurquesaClaro)
//        colorCode.add(R.color.aquamarine)
//        colorCode.add(R.color.lightSkyBlue)
//        colorCode.add(R.color.violet)
//        colorCode.add(R.color.verdeAmarillo)
//        val numero = Random.nextInt(colorCode.size)
//        return colorCode.get(numero)
//
//    }

    private fun getRandomColor(): Int {
        val colorCode = mutableListOf(
            R.color.lilaclaro,
            R.color.verdeTurquesaClaro,
            R.color.aquamarine,
            R.color.lightSkyBlue,
            R.color.violet,
            R.color.verdeAmarillo
        )
        val numero = Random.nextInt(colorCode.size)
        return colorCode[numero]
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