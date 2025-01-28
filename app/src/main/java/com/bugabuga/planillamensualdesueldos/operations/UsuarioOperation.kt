package com.bugabuga.planillamensualdesueldos.operations

import com.bugabuga.planillamensualdesueldos.models.Empleado
import com.bugabuga.planillamensualdesueldos.models.Usuario
import com.mysql.jdbc.Statement

object UsuarioOperation {

    fun registrarUsuario(usuario: Usuario) {
        val connection = MySQLconnection.getConexion().prepareStatement(
            "INSERT INTO usuarios (nombre, apellido, email, password) VALUES (?, ?, ?, ?);",
            Statement.RETURN_GENERATED_KEYS
        )
        connection.setString(1, usuario.nombre)
        connection.setString(2, usuario.apellido)
        connection.setString(3, usuario.correo)
        connection.setString(4, usuario.password)

        connection.executeUpdate()
        connection.close()
    }

    fun listar(dato: String): List<Usuario> {
        try {
            var lista = mutableListOf<Usuario>()

            val ps = MySQLconnection.getConexion().prepareStatement(
                "SELECT id, nombre, apellido, email, password FROM usuarios WHERE nombre LIKE concat('%',?,'%');"
            )

            ps.setString(1, dato) // Reemplaza el parámetro ? con el valor de dato

            val rs = ps.executeQuery()

            while (rs.next()) {
                lista.add(
                    Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("password")
                    )
                )
            }
            rs.close()
            ps.close()

            return lista
        } catch (e: Exception) {
            println("Error en la función listar: ${e.message}")
            return emptyList()
        }
    }

}