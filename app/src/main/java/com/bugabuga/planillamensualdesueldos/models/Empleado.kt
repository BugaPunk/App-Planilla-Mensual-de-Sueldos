package com.bugabuga.planillamensualdesueldos.models

data class Empleado(
    var id: Int = 0,
    var nombre: String = "",
    var apellidos: String = "",
    var fechaIngreso: String = "",
    var cargo: String = "",
    var haberBasico: Double = 0.0
) {
    // Funciones de calculo de la segunda tabla
    fun calcularAniosDeTrabajo(): Int {
        val anioIngreso = fechaIngreso.split("/")[2].toInt()
        val anioActual = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)
        return anioActual - anioIngreso
    }

    // Determinar categoría según años de trabajo
    fun determinarCategoria(): String {
        val anios = calcularAniosDeTrabajo()
        return when {
            anios in 1..4 -> "D"
            anios in 5..8 -> "C"
            anios in 9..13 -> "B"
            anios > 13 -> "A"
            else -> "Sin categoría"
        }
    }

    // Calcular bono de movilidad
    fun calcularBonoMovilidad(): Double {
        return if (cargo.lowercase() == "mensajero") 200.0 else 0.0
    }

    // Calcular bono extra según categoría
    fun calcularBonoExtra(): Double {
        return when (determinarCategoria()) {
            "A" -> haberBasico * 0.10
            "B" -> haberBasico * 0.07
            "D" -> haberBasico * 0.07
            else -> 0.0
        }
    }

    // Calcular bono antigüedad según categoría
    fun calcularBonoAntiguedad(): Double {
        val anios = calcularAniosDeTrabajo()
        return when (determinarCategoria()) {
            "A" -> haberBasico * 0.035 * anios
            "B" -> haberBasico * 0.03 * anios
            "C" -> haberBasico * 0.027 * anios
            "D" -> haberBasico * 0.022 * anios
            else -> 0.0
        }
    }

    // Calcular total ganado
    fun calcularTotalGanado(): Double {
        return haberBasico + calcularBonoMovilidad() + calcularBonoExtra() + calcularBonoAntiguedad()
    }

    // Calcular descuento IVA
    fun calcularDescuentoIVA(): Double {
        return if (haberBasico >= 3000) calcularTotalGanado() * 0.13 else 0.0
    }

    // Calcular descuento AFP
    fun calcularDescuentoAFP(): Double {
        return calcularTotalGanado() * 0.055
    }

    // Calcular descuento Club
    fun calcularDescuentoClub(): Double {
        return if (haberBasico >= 1900) 100.0 else 0.0
    }

    // Calcular total descuentos
    fun calcularTotalDescuentos(): Double {
        return calcularDescuentoIVA() + calcularDescuentoAFP() + calcularDescuentoClub()
    }

    // Calcular líquido pagable
    fun calcularLiquidoPagable(): Double {
        return calcularTotalGanado() - calcularTotalDescuentos()
    }
}
