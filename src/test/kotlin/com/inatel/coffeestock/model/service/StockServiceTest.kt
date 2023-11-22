package com.inatel.coffeestock.model.service

import com.inatel.coffeestock.model.entity.Stock
import com.inatel.coffeestock.model.repository.interfaces.StockRepository
import com.inatel.coffeestock.utils.exception.ElementAlreadyExistsException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

class StockServiceTest {

    private lateinit var stockRepository: StockRepository
    private lateinit var stockService: StockService

    private val _stocks = mutableListOf<Stock>(
            Stock(11111, 30, "Arabic", 20.0, 123456),
            Stock(22222, 40, "Arabic", 30.0, 234567),
            Stock(33333, 50, "Arabic", 40.0, 345678)
    )

    @BeforeEach
    fun setup(){
        stockRepository = mockk<StockRepository>()
        stockService = StockService(stockRepository)
    }

    @Nested
    @DisplayName("Test scenario for GET STOCKS")
    inner class GetStocks{

        @Test
        @DisplayName("should provide a collection of stocks with desired number of stocks")
        fun verifyNumberOfStocks() {
            // given
            val numberOfStocks = 3
            every { stockRepository.getStocks()} returns _stocks

            // when
            val stocks = stockService.getStocks()

            // then
            assertEquals(numberOfStocks, stocks.size)
        }


        @Test
        @DisplayName("verifying if the retrieved stocks are correct")
        fun correctStocksRetrieved() {
            // given
            every { stockRepository.getStocks()} returns _stocks

            // when
            val stocks = stockService.getStocks()

            // then
            assertAll(
                    "Stocks",
                    {
                        _stocks.forEach(
                                { assertTrue(stocks.contains(it)) }
                        )
                    }
            )

        }

        @Test
        @DisplayName("verifying number of times that getStocks method was invoked")
        fun verifyNumberOfTimesCallingMethod() {
            // given
            every { stockRepository.getStocks()} returns _stocks

            // when
            stockService.getStocks()

            // then
            verify(exactly = 1) {
                stockRepository.getStocks()
            }

        }
    }

    @Nested
    @DisplayName("Test scenario for GET STOCK")
    inner class GetStock{
        @Test
        @DisplayName("should provide the stock with desired id")
        fun verifyCorrectStockRetrieved() {
            // given
            val desiredStockID: Long = 11111
            val desiredStock = Stock(11111, 30, "Arabic", 20.0, 123456)
            every { stockRepository.getStock(desiredStockID)} returns _stocks.get(0)

            // when
            val stock = stockService.getStock(desiredStockID)
        
            // then
            assertEquals(desiredStock, stock)
        }

        @Test
        @DisplayName("should throw NoSuchElementException when doesn't find stock id")
        fun verifyIncorrectStockRetrieved() {
            // given
            val incorrectStockID: Long = 9999
            every { stockRepository.getStock(incorrectStockID)}
                    .throws(NoSuchElementException("Could not find a stock with given ID $incorrectStockID"))

            // when / then
            assertThrows(NoSuchElementException::class.java){
                stockService.getStock(incorrectStockID)
            }
        }
    }
    
    @Nested
    @DisplayName("Test scenario for CREATE STOCK")
    inner class CreateStock{

        @Test
        @DisplayName("should provide the created stock ")
        fun verifyCorrectStockCreated() {
            // given
            val newStock = Stock(44444, 20, "Arabic", 50.0, 234567)
            every { stockRepository.createStock(newStock)} returns newStock

            // when
            val returnedStock = stockService.createStock(newStock)

            // then
            assertEquals(newStock, returnedStock)
        }

        @Test
        @DisplayName("should throw ElementAlreadyExistsException when stock already exists")
        fun verifyIncorrectStockCreated() {
            // given
            val newStock = Stock(11111, 20, "Arabic", 50.0, 234567)
            every { stockRepository.createStock(newStock) }.throws(ElementAlreadyExistsException("Stock with given ${newStock.getId()} already exists"))

            // when / then
            assertThrows(ElementAlreadyExistsException::class.java) {
                stockService.createStock(newStock)
            }
        }
    }
    @Nested
    @DisplayName("Test scenario for UPDATE STOCK")
    inner class UpdateStock{
        @Test
        @DisplayName("should provide the updated stock")
        fun verifyCorrectStockUpdated() {
            // given
            val updatedStock = Stock(44444, 50, "Arabic", 40.0, 123456)
            every { stockRepository.updateStock(updatedStock)} returns updatedStock

            // when
            val returnedStock = stockService.updateStock(updatedStock)

            // then
            assertEquals(updatedStock, returnedStock)
        }

        @Test
        @DisplayName("should throw NoSuchElementException when doesn't find stock id")
        fun verifyIncorrectStockUpdated() {
            // given
            val updatedStock = Stock(9999, 50, "Arabic", 40.0, 123456)
            every { stockRepository.updateStock(updatedStock)}.throws(NoSuchElementException("Could not find a client with given ID ${updatedStock.getId()}"))

            // when / then
            assertThrows(NoSuchElementException::class.java){
                stockService.updateStock(updatedStock)
            }

        }
    }

    @Nested
    @DisplayName("Test scenario for DELETE STOCK")
    inner class DeleteStock {
        @Test
        @DisplayName("should provide the deleted stock")
        fun verifyCorrectStockDeleted() {
            // given
            val targetStockId: Long = 22222
            val expectedValue = Unit
            //val deletedStock = Stock(22222, 40, "Arabic", 30.0, 234567)
            every { stockRepository.deleteStock(targetStockId) } returns Unit

            // when
            val returnedValue = stockService.deleteStock(targetStockId)

            // then
            assertEquals(expectedValue, returnedValue)
        }

        @Test
        @DisplayName("should throw NoSuchElementException when doesn't find client id")
        fun verifyIncorrectClientDeleted() {
            // given
            val targetStockId: Long = 9999
            every { stockRepository.deleteStock(targetStockId) }.throws(NoSuchElementException("Could not find a stock with given ID ${targetStockId}"))

            // when / then
            assertThrows(NoSuchElementException::class.java) {
                stockService.deleteStock(targetStockId)
            }
        }
    }
}
