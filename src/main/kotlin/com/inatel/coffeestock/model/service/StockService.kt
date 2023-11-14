package com.inatel.coffeestock.model.service

import com.inatel.coffeestock.model.entity.Stock
import com.inatel.coffeestock.model.repository.interfaces.StockRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class StockService(
    @Qualifier("mock_stock") private val stockRepository: StockRepository
){
    fun getStocks() : Collection<Stock> = stockRepository.getStocks()

    fun getStock(id : Long) : Stock = stockRepository.getStock(id)

    fun createStock(newStock : Stock) : Stock = stockRepository.createStock(newStock)

    fun updateStock(updatedStock : Stock) : Stock = stockRepository.updateStock(updatedStock)

    fun deleteStock(id : Long) : Unit = stockRepository.deleteStock(id)
}