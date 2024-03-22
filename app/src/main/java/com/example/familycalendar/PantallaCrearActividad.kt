package com.example.familycalendar

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.familycalendar.dataBase.ActividadClass
import com.example.familycalendar.dataBase.SQLManager
import com.example.familycalendar.databinding.ActivityPantallaCrearActividadBinding
import com.example.familycalendar.fragment.DatePickerFragmentCrearActividad
import com.example.familycalendar.fragment.TimePickerFragmentHoraFinalCrearActividad
import com.example.familycalendar.fragment.TimePickerFragmentHoraInicialCrearActividad

class PantallaCrearActividad : AppCompatActivity(), CrearActividadClickListener, FechaSeleccionadaListener {

    private lateinit var binding: ActivityPantallaCrearActividadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaCrearActividadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editTextFechaCrearActividad.setOnClickListener { mostrarDatePickerDialog() }
        binding.editTextHoraInicial.setOnClickListener { mostrarTimePickerDialogHoraInicial() }
        binding.editTextHoraFinal.setOnClickListener { mostrarTimePickerDialogHoraFinal() }

        binding.botonListoCrearActividad.setOnClickListener { onCrearActividadClicked() }
    }

    override fun onCrearActividadClicked() {
        if (testData()) {
            val datos = ActividadClass(
                binding.editTextTituloActividad.text.toString(),
                binding.editTextDetallesCrearActividad.text.toString(),
                binding.editTextFechaCrearActividad.text.toString(),
                binding.editTextHoraInicial.text.toString(),
                binding.editTextHoraFinal.text.toString()
            )
            val sqlManager = SQLManager(this)
            val response = sqlManager.addActividad(datos)
            if (response) {
                Toast.makeText(this, "Actividad creada", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Todos los datos son obligatorios", Toast.LENGTH_SHORT)
        }
        val intent = Intent(this, PantallaPrincipalActividades::class.java)
        startActivity(intent)
    }

    fun testData(): Boolean {
        var response = true
        if (binding.editTextTituloActividad.text.isEmpty() || binding.editTextDetallesCrearActividad.text.isEmpty()) {
            response = false
        }
        return response
    }


    private fun mostrarTimePickerDialogHoraInicial() {
        val timePicker =
            TimePickerFragmentHoraInicialCrearActividad { onTimeSelectedHoraInicial(it) }
        timePicker.show(supportFragmentManager, "timeInitial")
    }

    private fun mostrarTimePickerDialogHoraFinal() {
        val timePicker = TimePickerFragmentHoraFinalCrearActividad { onTimeSelectedHoraFinal(it) }
        timePicker.show(supportFragmentManager, "timeFinal")
    }

    private fun mostrarDatePickerDialog() {
        val datePicker =
            DatePickerFragmentCrearActividad { day, month, year ->
                onFechaSeleccionada(
                    day,
                    month,
                    year
                )
            }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onTimeSelectedHoraInicial(tiempoInicial: String) {
        binding.editTextHoraInicial.setText("$tiempoInicial")
    }

    private fun onTimeSelectedHoraFinal(tiempoFinal: String) {
        binding.editTextHoraFinal.setText("$tiempoFinal")
    }

    override fun onFechaSeleccionada(day: Int, month: Int, year: Int) {
        binding.editTextFechaCrearActividad.setText("$day/$month/$year")
    }
}