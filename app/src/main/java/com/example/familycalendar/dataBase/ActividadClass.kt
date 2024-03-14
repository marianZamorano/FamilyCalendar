package com.example.familycalendar.dataBase

class ActividadClass {
    var numero: Int = 0
    var titulo: String = ""
    var fecha: String = ""
    var horaInicial: String = ""
    var horaFinal: String = ""
    var detalle: String = ""
    constructor(numero: Int, titulo: String, fecha: String, horaInicial: String, horaFinal: String, detalle: String) {
        this.titulo = titulo
        this.fecha = fecha
        this.horaInicial = horaInicial
        this.horaFinal = horaFinal
        this.detalle = detalle
    }
}