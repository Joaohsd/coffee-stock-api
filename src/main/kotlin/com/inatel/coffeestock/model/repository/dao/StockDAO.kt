package com.inatel.coffeestock.model.repository.dao

import com.inatel.coffeestock.model.entity.Stock
import com.inatel.coffeestock.model.repository.interfaces.StockRepository
import org.springframework.stereotype.Repository
import java.sql.SQLException

@Repository("stock")
class StockDAO : DatabaseDAO(), StockRepository {

    override fun getStocks(): Collection<Stock> {
        val stocks = ArrayList<Stock>()

        connectToDB()

        val sql = "SELECT * FROM stock"

        try {
            _st = _con?.createStatement()
            _rs = _st?.executeQuery(sql)
            println("Stocks list: ")
            while (_rs?.next() == true) {
                val stockAux = Stock(_rs!!.getInt(1), _rs!!.getInt(2),
                        _rs!!.getString(3), _rs!!.getDouble(4), _rs!!.getString(5),
                        _rs!!.getString(6))
                println("id = " + stockAux.getId())
                println("Quantity = " + stockAux.getQuantity())
                println("Coffee type = " + stockAux.getCoffeeType())
                println("Coffee Cupping = " + stockAux.getCoffeeCupping())
                println("Status = " + stockAux.getStatus())
                println("Client Cpf = " + stockAux.getClientCpf())
                println("--------------------------------")
                stocks.add(stockAux)
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
        return stocks
    }

    override fun getStocksFromClient(clientCpf: String): Collection<Stock> {
        val stocks = ArrayList<Stock>()

        connectToDB()

        val sql = "SELECT * FROM stock WHERE client_cpf=?"

        try {
            _pst = _con?.prepareStatement(sql)
            _pst?.setString(1, clientCpf)
            _rs = _pst?.executeQuery()
            println("Stocks list: ")
            while (_rs?.next() == true) {
                val stockAux = Stock(_rs!!.getInt(1), _rs!!.getInt(2),
                        _rs!!.getString(3), _rs!!.getDouble(4), _rs!!.getString(5),
                        _rs!!.getString(6))
                println("id = " + stockAux.getId())
                println("Quantity = " + stockAux.getQuantity())
                println("Coffee type = " + stockAux.getCoffeeType())
                println("Coffee Cupping = " + stockAux.getCoffeeCupping())
                println("Status = " + stockAux.getStatus())
                println("Client Cpf = " + stockAux.getClientCpf())
                println("--------------------------------")
                stocks.add(stockAux)
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
        return stocks
    }

    override fun getStock(id: Int): Stock? {
        connectToDB()

        val sql = "SELECT * FROM stock WHERE id=?"

        var stock: Stock? = null

        try {
            _pst = _con?.prepareStatement(sql)
            _pst?.setInt(1, id)
            _rs = _pst?.executeQuery()

            if(_rs?.next() == true){
                stock = Stock(_rs!!.getInt(1), _rs!!.getInt(2),
                        _rs!!.getString(3), _rs!!.getDouble(4), _rs!!.getString(5),
                        _rs!!.getString(6))
                println("id = " + stock.getId())
                println("Quantity = " + stock.getQuantity())
                println("Coffee type = " + stock.getCoffeeType())
                println("Coffee Cupping = " + stock.getCoffeeCupping())
                println("Status = " + stock.getStatus())
                println("Client Cpf = " + stock.getClientCpf())
                println("--------------------------------")
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
        return stock
    }

    override fun createStock(newStock: Stock): Stock? {
        var stockCreated: Stock? = null

        connectToDB()

        val sql = "INSERT INTO stock (quantity, coffeeType, coffeeCupping, status, client_cpf) VALUES (?,?,?,?,?)"

        try {
            _pst = _con?.prepareStatement(sql)
            _pst?.setInt(1, newStock.getQuantity())
            _pst?.setString(2, newStock.getCoffeeType())
            _pst?.setDouble(3, newStock.getCoffeeCupping())
            _pst?.setString(4, newStock.getStatus())
            _pst?.setString(5, newStock.getClientCpf())
            _pst?.execute()
            stockCreated = newStock
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
        return stockCreated
    }

    override fun updateStock(updatedStock: Stock): Stock? {
        connectToDB()
        val sql = "UPDATE stock SET quantity=?, coffeeType=?, coffeeCupping=?, status=?, client_cpf=? WHERE id=?"
        try {
            _pst = _con?.prepareStatement(sql)
            _pst?.setInt(1, updatedStock.getQuantity())
            _pst?.setString(2, updatedStock.getCoffeeType())
            _pst?.setDouble(3, updatedStock.getCoffeeCupping())
            _pst?.setString(4, updatedStock.getStatus())
            _pst?.setString(5, updatedStock.getClientCpf())
            _pst?.setInt(6, updatedStock.getId())
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

        return updatedStock
    }

    override fun updateStockStatus(id: Int, status: String): Boolean {
        var success:Boolean = false
        connectToDB()
        val sql = "UPDATE stock SET status=? WHERE id=?"
        try {
            _pst = _con?.prepareStatement(sql)
            _pst?.setString(1, status)
            _pst?.setInt(2, id)
            _pst?.execute()
            success = true
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
        return success
    }

    override fun deleteStock(id: Int) : Boolean {
        var success : Boolean = false
        connectToDB()
        val sql = "DELETE FROM stock where id=?"
        try {
            _pst = _con?.prepareStatement(sql)
            _pst?.setInt(1, id)
            _pst?.execute()
            success = true
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
        return success
    }
}