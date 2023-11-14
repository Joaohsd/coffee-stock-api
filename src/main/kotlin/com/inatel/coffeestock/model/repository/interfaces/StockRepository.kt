package com.inatel.coffeestock.model.repository.interfaces
import com.inatel.coffeestock.model.entity.Stock
interface StockRepository {

    fun getStocks() : Collection<Stock>

    fun getStock(id:Long) : Stock

    fun createStock(newStock:Stock) : Stock

    fun updateStock(updatedStock:Stock) : Stock

    fun deleteStock(id:Long) : Unit

}