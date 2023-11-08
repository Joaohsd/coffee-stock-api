package com.inatel.coffeestock.model.service

import com.inatel.coffeestock.model.entity.Stock
import com.inatel.coffeestock.model.repository.StockRepository
import org.springframework.stereotype.Service

@Service
class StockService(
    private val StockRepository: StockRepository
){
    fun getStocks() : Collection<Stock> = StockRepository.getStocks()

    fun getStock(id : Long) : Stock = StockRepository.getStock(id)

    fun createStock(newStock : Stock) : Stock = StockRepository.createStock(newStock)

    fun updateStock(updatedStock : Stock) : Stock = StockRepository.updateStock(updatedStock)

    fun deleteStock(id : Long) : Stock = StockRepository.deleteStock(id)
}