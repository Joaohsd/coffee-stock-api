package com.inatel.coffeestock.model.repository.interfaces
import com.inatel.coffeestock.model.entity.Stock
interface StockRepository {

    fun getStocks() : Collection<Stock>

    fun getStocksFromClient(clientCpf:String) : Collection<Stock>

    fun getStock(id:Int) : Stock?

    fun createStock(newStock:Stock) : Stock?

    fun updateStock(updatedStock:Stock) : Stock?

    fun updateStockStatus(id:Int, status:String) : Boolean

    fun deleteStock(id:Int) : Boolean

}