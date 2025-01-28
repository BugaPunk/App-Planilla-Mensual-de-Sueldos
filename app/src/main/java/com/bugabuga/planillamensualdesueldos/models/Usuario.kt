package com.bugabuga.planillamensualdesueldos.models

data class Usuario(
    var id: Int = 0,
    val nombre: String,
    val apellido: String,
    val correo: String,
    val password: String
) {
}