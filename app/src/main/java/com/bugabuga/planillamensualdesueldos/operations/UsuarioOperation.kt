package com.bugabuga.planillamensualdesueldos.operations

import com.bugabuga.planillamensualdesueldos.models.Usuario
import com.mysql.jdbc.Statement
import java.sql.ResultSet

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

    fun validarUsuario(email: String, password: String): Usuario? {
        var usuario: Usuario? = null

        try {
            val contrasennaCifrada = UtilsCrypto.hashSHA256(password)

            val ps = MySQLconnection.getConexion().prepareStatement(
                "SELECT id, nombre, apellido, email, password FROM usuarios WHERE email = ? AND password = ?;"
            )
            ps.setString(1, email)
            ps.setString(2, contrasennaCifrada) // Comparar con el hash almacenado

            val rs: ResultSet = ps.executeQuery()

            if (rs.next()) {
                usuario = Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("email"),
                    rs.getString("password")
                )
            }
            rs.close()
            ps.close()
        } catch (e: Exception) {
            println("Error en la función validarUsuario: ${e.message}")
        }
        return usuario
    }
}