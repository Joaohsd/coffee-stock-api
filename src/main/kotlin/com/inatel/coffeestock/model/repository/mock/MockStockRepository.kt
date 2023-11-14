package com.inatel.coffeestock.model.repository.mock

import com.inatel.coffeestock.model.entity.Stock
import com.inatel.coffeestock.model.repository.interfaces.StockRepository
import org.springframework.stereotype.Repository

@Repository("mock_stock")
class MockStockRepository : StockRepository {
    override fun getStocks(): Collection<Stock> {
        TODO("Not yet implemented")
    }

    override fun getStock(id: Long): Stock {
        TODO("Not yet implemented")
    }

    override fun createStock(newStock: Stock): Stock {
        TODO("Not yet implemented")
    }

    override fun updateStock(updatedStock: Stock): Stock {
        TODO("Not yet implemented")
    }

    override fun deleteStock(id: Long) {
        TODO("Not yet implemented")
    }
}