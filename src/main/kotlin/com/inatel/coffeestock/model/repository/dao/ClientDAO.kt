package com.inatel.coffeestock.model.repository.dao

import com.inatel.coffeestock.model.entity.Client
import com.inatel.coffeestock.model.repository.interfaces.ClientRepository
import org.springframework.stereotype.Repository
import java.sql.SQLException
import java.time.LocalDate

@Repository("client")
class ClientDAO: DatabaseDAO(),ClientRepository {

    override fun getClients(): Collection<Client> {
        val clients = ArrayList<Client>()

        connectToDB()

        val sql = "SELECT * FROM client"

        try {
            _st = _con?.createStatement()
            _rs = _st?.executeQuery(sql)
            println("Clients list: ")
            while (_rs?.next() == true) {
                val clientAux = Client(_rs!!.getString(1), _rs!!.getString(2),
                        _rs!!.getString(3), _rs!!.getString(4), _rs!!.getString(5),
                        _rs!!.getBoolean(6), _rs!!.getString(7))
                println("name = " + clientAux.getName())
                println("cpf = " + clientAux.getCpf())
                println("birth date = " + clientAux.getBirthDate())
                println("estate = " + clientAux.getEstate())
                println("email = " + clientAux.getEmail())
                println("isAdmin = " + clientAux.isAdmin())
                println("password = " + clientAux.getPassword())
                println("--------------------------------")
                clients.add(clientAux)
            }
        } catch (e: SQLException) {
            println("Erro: " + e.message)
        } finally {
            try {
                _con?.close()
                _st?.close()
            } catch (e: SQLException) {
                println("Erro: " + e.message)
            }
        }
        return clients
    }

    override fun getClient(cpf: String): Client? {
        connectToDB()

        val sql = "SELECT * FROM client WHERE cpf=?"

        var client:Client? = null

        try {
            _pst = _con?.prepareStatement(sql)
            _pst?.setString(1, cpf)
            _rs = _pst?.executeQuery()

            if(_rs?.next() == true){
                client = Client(_rs!!.getString(1), _rs!!.getString(2),
                        _rs!!.getString(3), _rs!!.getString(4), _rs!!.getString(5),
                        _rs!!.getBoolean(6), _rs!!.getString(7))
                println("name = " + client.getName())
                println("cpf = " + client.getCpf())
                println("birth date = " + client.getBirthDate())
                println("estate = " + client.getEstate())
                println("email = " + client.getEmail())
                println("isAdmin = " + client.isAdmin())
                println("password = " + client.getPassword())
                println("--------------------------------")
            }
        } catch (e: SQLException) {
            println("Erro: " + e.message)
        } finally {
            try {
                _con?.close()
                _st?.close()
            } catch (e: SQLException) {
                println("Erro: " + e.message)
            }
        }
        return client
    }

    override fun createClient(newClient: Client): Client? {
        connectToDB()

        val sql = "INSERT INTO client (cpf, name, birthDate, estate, email, isAdmin, password) VALUES (?,?,?,?,?,?,?)"

        try {
            _pst = _con?.prepareStatement(sql)
            _pst?.setString(1, newClient.getCpf())
            _pst?.setString(2, newClient.getName())
            _pst?.setString(3, newClient.getBirthDate())
            _pst?.setString(4, newClient.getEstate())
            _pst?.setString(5, newClient.getEmail())
            _pst?.setBoolean(6, newClient.isAdmin())
            _pst?.setString(7, newClient.getPassword())
            _pst?.execute()
            return newClient
        } catch (exc: SQLException) {
            println("Erro: " + exc.message)
        } finally {
            try {
                _con?.close()
                _pst?.close()
            } catch (exc: SQLException) {
                println("Erro: " + exc.message)
            }
        }
        return null
    }

    override fun updateClient(updatedClient: Client): Client? {
        connectToDB()
        val sql = "UPDATE client SET name=?, birthDate=?, estate=?, email=?, isAdmin=?, password=? WHERE cpf=?"
        try {
            _pst = _con?.prepareStatement(sql)
            _pst?.setString(1, updatedClient.getName())
            _pst?.setString(2, updatedClient.getBirthDate())
            _pst?.setString(3, updatedClient.getEstate())
            _pst?.setString(4, updatedClient.getEmail())
            _pst?.setBoolean(5, updatedClient.isAdmin())
            _pst?.setString(6, updatedClient.getPassword())
            _pst?.setString(7, updatedClient.getCpf())
            _pst?.execute()
        } catch (ex: SQLException) {
            println("Erro = " + ex.message)
        } finally {
            try {
                _con?.close()
                _pst?.close()
            } catch (exc: SQLException) {
                println("Erro: " + exc.message)
            }
        }

        return updatedClient
    }

    override fun deleteClient(cpf: String) : Unit {
        connectToDB()
        val sql = "DELETE FROM client where cpf=?"
        try {
            _pst = _con?.prepareStatement(sql)
            _pst?.setString(1, cpf)
            _pst?.execute()
        } catch (ex: SQLException) {
            println("Erro = " + ex.message)
        } finally {
            try {
                _con?.close()
                _pst?.close()
            } catch (exc: SQLException) {
                println("Erro: " + exc.message)
            }
        }
        return Unit
    }

}