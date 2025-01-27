package com.bugabuga.planillamensualdesueldos.models

data class BeneficiosEmpleado(
    var id: Int = 0,
    var empleado_id: Int = 0,
    var anos_trabajo: Int = 0,
    var categoria: String = "",
    var bono_movilidad: Double = 0.0,
    var bono_extra: Double = 0.0,
    var bono_antiguedad: Double = 0.0,
    var total_ganado: Double = 0.0,
    var iva: Double = 0.0,
    var afp: Double = 0.0,
    var club: Double = 0.0,
    var total_descuento: Double = 0.0,
    var liquido_pagable: Double = 0.0
) {
}