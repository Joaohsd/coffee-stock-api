package com.inatel.coffeestock.model.service

import com.inatel.coffeestock.model.entity.Client
import com.inatel.coffeestock.model.entity.Stock
import com.inatel.coffeestock.model.repository.StockRepository
import com.inatel.coffeestock.utils.exception.ElementAlreadyExistsException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.*
import java.time.LocalDate
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
            Assertions.assertAll(
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
    @DisplayName("Test scenario for Create Stock")
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
            val newStock = Stock(1111, 20, "Arabic", 50.0, 234567)
            every { stockRepository.createStock(newStock) }.throws(ElementAlreadyExistsException("Stock with given ${newStock.getId()} already exists"))

            // when / then
            assertThrows(ElementAlreadyExistsException::class.java) {
                stockService.createStock(newStock)
            }
        }
    }

}



