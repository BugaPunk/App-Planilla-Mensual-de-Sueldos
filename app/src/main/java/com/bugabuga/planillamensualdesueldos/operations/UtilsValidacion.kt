package com.bugabuga.planillamensualdesueldos.operations

object UtilsValidacion {
    // Expresión regular para validar correos electrónicos
    private const val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"

    fun validarCorreo(email: String): Boolean {
        return email.matches(EMAIL_REGEX.toRegex())
    }
}