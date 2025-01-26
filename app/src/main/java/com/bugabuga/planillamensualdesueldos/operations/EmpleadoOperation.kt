package com.bugabuga.planillamensualdesueldos.operations

import com.bugabuga.planillamensualdesueldos.models.Empleado

object EmpleadoOperation {
    fun listar(dato: String): List<Empleado> {
        var lista = mutableListOf<Empleado>()

        val conexion = MySQLconnection.getConexion().prepareStatement(
            "SELECT id, nombre, apellidos, fecha_ingreso, cargo, haber_basico FROM empleado WHERE nombre LIKE concat('%', ?, '%') OR apellidos LIKE concat('%', ?, '%');"
        )
        conexion.setString(1, dato)
        val result_set = conexion.executeQuery()

        while (result_set.next()) {
            lista.add(
                Empleado(
                    result_set.getInt("id"),
                    result_set.getString("nombre"),
                    result_set.getString("apellidos"),
                    result_set.getString("fecha_ingreso"),
                    result_set.getString("cargo"),
                    result_set.getDouble("haber_basico")
                )
            )
        }
        result_set.close()
        conexion.close()
        return lista
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