package com.inatel.coffeestock.model.service

import com.inatel.coffeestock.model.entity.Stock
import com.inatel.coffeestock.model.repository.StockRepository
import org.springframework.stereotype.Service

@Service
class StockService(
    private val stockRepository: StockRepository
){
    fun getStocks() : Collection<Stock> = stockRepository.getStocks()

    fun getStock(id : Long) : Stock = stockRepository.getStock(id)

    fun createStock(newStock : Stock) : Stock = stockRepository.createStock(newStock)

    fun updateStock(updatedStock : Stock) : Stock = stockRepository.updateStock(updatedStock)

    fun deleteStock(id : Long) : Unit = stockRepository.deleteStock(id)
}