package com.bugabuga.planillamensualdesueldos.operations

import java.sql.Connection
import java.sql.DriverManager

object MySQLconnection {
    fun getConexion(): Connection {
        Class.forName("com.mysql.jdbc.Driver")
        return DriverManager.getConnection(
            "jdbc:mysql://autorack.proxy.rlwy.net:31331/db_planilla",
            "root",
            "HviisPFigtqQCwmiAeYzkacqUuVSApmw"
        )
    }
}