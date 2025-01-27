package com.bugabuga.planillamensualdesueldos.operations

import com.bugabuga.planillamensualdesueldos.models.BeneficiosEmpleado
import com.bugabuga.planillamensualdesueldos.models.Empleado
import com.mysql.jdbc.Statement

object EmpleadoOperation {
    private var empleadoId: Int = 0
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
            "INSERT INTO empleado (nombre, apellidos, fecha_ingreso, cargo, haber_basico) VALUES (?, ?, ?, ?, ?);",
            Statement.RETURN_GENERATED_KEYS
        )
        conexion.setString(1, empleado.nombre)
        conexion.setString(2, empleado.apellidos)
        conexion.setString(3, empleado.fechaIngreso)
        conexion.setString(4, empleado.cargo)
        conexion.setDouble(5, empleado.haberBasico)

        conexion.executeUpdate()

        val rs = conexion.getGeneratedKeys()
        if (rs.next()) {
            empleadoId = rs.getInt(1)
        }

        conexion.close()
    }

    private fun registrarB(sID: Int, empleado: Empleado, beneficiosEmpleado: BeneficiosEmpleado) {
        val conexion = MySQLconnection.getConexion().prepareStatement(
            "INSERT INTO beneficios_empleado (empleado_id, anos_trabajo, categoria, bono_movilidad, bono_extra, bono_antiguedad, total_ganado, iva, afp, club, total_descuento, liquido_pagable) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
        )
        conexion.setInt(1, sID)
        conexion.setInt(2, empleado.calcularAniosDeTrabajo())
        conexion.setString(3, empleado.determinarCategoria())
        conexion.setDouble(4, empleado.calcularBonoMovilidad())
        conexion.setDouble(5, empleado.calcularBonoExtra())
        conexion.setDouble(6, empleado.calcularBonoAntiguedad())
        conexion.setDouble(7, empleado.calcularTotalGanado())
        conexion.setDouble(8, empleado.calcularDescuentoIVA())
        conexion.setDouble(9, empleado.calcularDescuentoAFP())
        conexion.setDouble(10, empleado.calcularDescuentoClub())
        conexion.setDouble(11, empleado.calcularTotalDescuentos())
        conexion.setDouble(12, empleado.calcularLiquidoPagable())

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
        if (empleado.id == 0){
            registrar(empleado)
        }
        else{
            actualizar(empleado)
        }
    }

    fun grabarB(empleado: Empleado, beneficiosEmpleado: BeneficiosEmpleado) {
        if (beneficiosEmpleado.id == 0){
            registrarB(this.empleadoId, empleado, beneficiosEmpleado)
        }
        else{
            //actualizar(beneficiosEmpleado)
        }
    }

    // El unico getter
    fun getEmpleadoId(): Int {
        return empleadoId
    }
}