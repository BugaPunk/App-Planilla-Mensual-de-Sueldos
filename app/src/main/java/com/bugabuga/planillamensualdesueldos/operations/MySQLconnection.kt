package com.bugabuga.planillamensualdesueldos.operations

import java.sql.Connection

object MySQLconnection {
    fun getConexion(): Connection {
        Class.forName("com.mysql.jdbc.Driver")
        return java.sql.DriverManager.getConnection(
            "jdbc:mysql://192.168.0.111:3306/db_planilla",
            "root",
            ""
        )
    }
}