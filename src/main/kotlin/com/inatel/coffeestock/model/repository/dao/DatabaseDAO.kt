package com.inatel.coffeestock.model.repository.dao

import java.sql.*


abstract class DatabaseDAO(){
    var _con: Connection? = null
    var _pst: PreparedStatement? = null
    var _st: Statement? = null
    var _rs: ResultSet? = null

    val _database = "coffee"
    val _user = "root"
    val _password = "root"
    val _url = "jdbc:mysql://localhost:3306/$_database?useTimezone=true&" +
            "serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true"

    public fun connectToDB() {
        try {
            _con = DriverManager.getConnection(_url, _user, _password)
            println("Connect successfully")
        } catch (exc: SQLException) {
            println("Error: " + exc.message)
        }
    }
}