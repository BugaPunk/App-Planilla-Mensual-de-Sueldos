package com.bugabuga.planillamensualdesueldos.operations

import java.sql.Connection
import java.sql.DriverManager

object MySQLconnection {
    fun getConexion(): Connection {
        Class.forName("com.mysql.jdbc.Driver")

        return DriverManager.getConnection(
            "jdbc:mysql://192.168.0.111:3306/db_planilla",
            "root",
            ""
        )
    }
}