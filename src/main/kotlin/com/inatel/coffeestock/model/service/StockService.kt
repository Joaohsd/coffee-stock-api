package com.inatel.coffeestock.model.service

import com.inatel.coffeestock.model.entity.Stock
import com.inatel.coffeestock.model.repository.interfaces.StockRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class StockService(
    @Qualifier("stock") private val stockRepository: StockRepository
){
    fun getStocks() : Collection<Stock> = stockRepository.getStocks()

    fun getStock(id : Int) : Stock? {
        return stockRepository.getStock(id)
                ?: throw NoSuchElementException("Stock with given ${id} does not exist")
    }

    fun getStocksFromClient(clientCpf:String) : Collection<Stock> = stockRepository.getStocksFromClient(clientCpf)

    fun createStock(newStock : Stock) : Stock? {
        return stockRepository.createStock(newStock)
                ?:throw NoSuchElementException("Cpf ${newStock.getClientCpf()} does not exist")
    }

    fun updateStock(updatedStock : Stock) : Stock? {
        if(stockRepository.getStock(updatedStock.getId()) == null){
            throw NoSuchElementException("Stock with given ${updatedStock.getId()} does not exist")
        }
        else{
            return stockRepository.updateStock(updatedStock)
        }
    }

    fun updateStockStatus(stockId: Int, stockStatus: String): Stock? {
        if(stockRepository.getStock(stockId) == null){
            throw NoSuchElementException("Stock with given ${stockId} does not exist")
        }
        else{
            if(stockRepository.updateStockStatus(stockId, stockStatus)){
                return stockRepository.getStock(stockId)
            }
        }
        return null
    }

    fun deleteStock(id : Int) : Unit {
        if(stockRepository.getStock(id) == null){
            throw NoSuchElementException("Stock with given ${id} does not exist")
        }
        else{
            if (stockRepository.deleteStock(id))
                return Unit
        }
    }
}