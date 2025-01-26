package com.bugabuga.planillamensualdesueldos.models

data class Empleado(
    var id: Int = 0,
    var nombre: String = "",
    var apellidos: String = "",
    var fechaIngreso: String = "",
    var cargo: String = "",
    var haberBasico: Double = 0.0
) {

}
