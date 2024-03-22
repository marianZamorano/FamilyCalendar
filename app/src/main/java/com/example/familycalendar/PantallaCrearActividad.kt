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

class PantallaCrearActividad : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaCrearActividadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaCrearActividadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.editTextFechaCrearActividad.setOnClickListener { mostrarDatePickerDialog() }
        binding.editTextHoraInicial.setOnClickListener { mostrarTimePickerDialogHoraInicial() }
        binding.editTextHoraFinal.setOnClickListener { mostrarTimePickerDialogHoraFinal() }
        binding.botonListoCrearActividad.setOnClickListener {
            if (testData()) {
                var datos = ActividadClass(
                    binding.editTextTituloActividad.text.toString(),
                    binding.editTextDetallesCrearActividad.text.toString(),
                    binding.editTextFechaCrearActividad.text.toString(),
                    binding.editTextHoraInicial.text.toString(),
                    binding.editTextHoraFinal.text.toString()
                )
                var sqlManager = SQLManager(this)
                var response = sqlManager.addActividad(this, datos)
                if (response) {
                    Toast.makeText(this, "Actividad creada", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Todos los datos son obligatorios", Toast.LENGTH_SHORT)
            }
            val intent = Intent(this, PantallaPrincipalActividades::class.java)
            startActivity(intent)
        }
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

    private fun onTimeSelectedHoraInicial(tiempoInicial: String) {
        binding.editTextHoraInicial.setText("$tiempoInicial")
    }

    private fun onTimeSelectedHoraFinal(tiempoFinal: String) {
        binding.editTextHoraFinal.setText("$tiempoFinal")
    }

    private fun mostrarDatePickerDialog() {
        val datePicker =
            DatePickerFragmentCrearActividad { day, month, year ->
                onDateSelected(
                    day,
                    month,
                    year
                )
            }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        binding.editTextFechaCrearActividad.setText("$day/$month/$year")
    }
}