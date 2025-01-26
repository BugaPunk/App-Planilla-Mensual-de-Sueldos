package com.bugabuga.planillamensualdesueldos.operations

import com.bugabuga.planillamensualdesueldos.models.Empleado

object EmpleadoOperation {
    fun listar(dato: String): List<Empleado> {
        try {
            var lista = mutableListOf<Empleado>()

            val ps = MySQLconnection.getConexion().prepareStatement(
                "SELECT id, nombre, apellidos, fecha_ingreso, cargo, haber_basico FROM empleado WHERE nombre LIKE concat('%',?,'%');"
            )

            ps.setString(1, dato) // Reemplaza el parámetro ? con el valor de dato

            val rs = ps.executeQuery()

            while (rs.next()) {
                lista.add(
                    Empleado(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("fecha_ingreso"),
                        rs.getString("cargo"),
                        rs.getDouble("haber_basico")
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

    private fun registrar(empleado: Empleado) {
        val conexion = MySQLconnection.getConexion().prepareStatement(
            "INSERT INTO empleado (nombre, apellidos, fecha_ingreso, cargo, haber_basico) VALUES (?, ?, ?, ?, ?);"
        )
        conexion.setString(1, empleado.nombre)
        conexion.setString(2, empleado.apellidos)
        conexion.setString(3, empleado.fechaIngreso)
        conexion.setString(4, empleado.cargo)
        conexion.setDouble(5, empleado.haberBasico)

        conexion.executeUpdate()
        conexion.close()
    }

    private fun actualizar(empleado: Empleado) {
        val conexion = MySQLconnection.getConexion().prepareStatement(
            "UPDATE empleado SET nombre = ?, apellidos = ?, fecha_ingreso = ?, cargo = ?, haber_basico = ? WHERE id = ?;"
        )
        conexion.setString(1, empleado.nombre)
        conexion.setString(2, empleado.apellidos)
        conexion.setString(3, empleado.fechaIngreso)
        conexion.setString(4, empleado.cargo)
        conexion.setDouble(5, empleado.haberBasico)
        conexion.setInt(6, empleado.id)

        conexion.executeUpdate()
        conexion.close()
    }

    fun eliminar(empleado: Empleado) {
        val conexion = MySQLconnection.getConexion().prepareStatement(
            "DELETE FROM empleado WHERE id = ?;"
        )
        conexion.setInt(1, empleado.id)

        conexion.executeUpdate()
        conexion.close()
    }

    fun grabar(empleado: Empleado) {
        if (empleado.id == 0)
            registrar(empleado)
        else
            actualizar(empleado)
    }
}