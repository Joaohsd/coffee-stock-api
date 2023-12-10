package com.inatel.coffeestock.model.service

import com.inatel.coffeestock.model.entity.Client
import com.inatel.coffeestock.model.entity.Stock
import com.inatel.coffeestock.model.repository.interfaces.ClientRepository
import com.inatel.coffeestock.model.repository.interfaces.StockRepository
import com.inatel.coffeestock.utils.exception.ElementAlreadyExistsException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

class StockServiceTest {

    private lateinit var stockRepository: StockRepository
    private lateinit var clientRepository: ClientRepository
    private lateinit var stockService: StockService


    private val _stocks = mutableListOf<Stock>(
            Stock(11111, 30, "Arabic", 20.0, "ALLOWED","123.456.789-10"),
            Stock(22222, 40, "Arabic", 30.0, "REFUSED", "999.999.999-99"),
            Stock(33333, 50, "Arabic", 40.0, "WAITING", "111.111.111-11")
    )

    @BeforeEach
    fun setup(){
        stockRepository = mockk<StockRepository>()
        clientRepository = mockk<ClientRepository>()
        stockService = StockService(stockRepository, clientRepository)
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
            val desiredStockID: Int = 11111
            val desiredStock = Stock(11111, 30, "Arabic", 20.0, "ALLOWED", "123.456.789-10")

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
            val incorrectStockID: Int = 99999

            every { stockRepository.getStock(incorrectStockID)} returns null

            // when / then
            assertThrows(NoSuchElementException::class.java){
                stockService.getStock(incorrectStockID)
            }
        }
    }

    @Nested
    @DisplayName("Test scenario for GET STOCKS FROM CLIENT")
    inner class GetStocksFromClient{
        @Test
        @DisplayName("should provide correct a list of stocks")
        fun verifyCorrectStocksRetrieved() {
            // given
            val client = Client("123.456.789-10","Fulano Ciclano",  "1999-1-1", "Santa Rita", "fulano@email.com.br", true, "fulano")
            val stocks = mutableListOf<Stock>(_stocks.get(0))

            every { clientRepository.getClient(client.getCpf())} returns client
            every { stockRepository.getStocksFromClient(client.getCpf())} returns stocks

            // when
            val stocksRetrieved = stockService.getStocksFromClient(client.getCpf())

            // then
            assertAll(
                    "Stocks",
                    {
                        stocks.forEach(
                                { assertTrue(stocksRetrieved.contains(it)) }
                        )
                    }
            )
        }

        @Test
        @DisplayName("should throw NoSuchElementException when doesn't find client with desired cpf")
        fun verifyIncorrectStocksBecauseOfClient() {
            // given
            val client = Client("000.000.000-00","Fulano Ciclano",  "1999-1-1", "Santa Rita", "fulano@email.com.br", true, "fulano")

            every { clientRepository.getClient(client.getCpf())} returns null

            // when / then
            assertThrows(NoSuchElementException::class.java){
                stockService.getStocksFromClient(client.getCpf())
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
            val client = Client("123.456.789-10","Fulano Ciclano",  "1999-1-1", "Santa Rita", "fulano@email.com.br", true, "fulano")
            val newStock = Stock(44444, 20, "Arabic", 50.0, "WAITING", client.getCpf())

            every { clientRepository.getClient(client.getCpf())} returns client
            every { stockRepository.createStock(newStock)} returns newStock

            // when
            val returnedStock = stockService.createStock(newStock)

            // then
            assertEquals(newStock, returnedStock)
        }

        @Test
        @DisplayName("should throw NoSuchElementException when client owner of stock does not exist")
        fun verifyIncorrectStockCreated() {
            // given
            val client = Client("000.000.000-00","Fulano Ciclano",  "1999-1-1", "Santa Rita", "fulano@email.com.br", true, "fulano")
            val newStock = Stock(11111, 20, "Arabic", 50.0, "WAITING", client.getCpf())

            every { clientRepository.getClient(client.getCpf())} returns null

            // when / then
            assertThrows(NoSuchElementException::class.java) {
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
            val client = Client("123.456.789-10","Fulano Ciclano",  "1999-1-1", "Santa Rita", "fulano@email.com.br", true, "fulano")
            val updatedStock = Stock(11111, 40, "Arabic", 20.0, "ALLOWED",client.getCpf())

            every { clientRepository.getClient(client.getCpf())} returns client
            every { stockRepository.getStock(updatedStock.getId())} returns updatedStock
            every { stockRepository.updateStock(updatedStock)} returns updatedStock

            // when
            val returnedStock = stockService.updateStock(updatedStock)

            // then
            assertEquals(updatedStock, returnedStock)
        }

        @Test
        @DisplayName("should throw NoSuchElementException when doesn't find stock with desired id")
        fun verifyIncorrectStockUpdated() {
            // given
            val client = Client("123.456.789-10","Fulano Ciclano",  "1999-1-1", "Santa Rita", "fulano@email.com.br", true, "fulano")
            val updatedStock = Stock(99999, 30, "Arabic", 20.0, "ALLOWED",client.getCpf())

            every { clientRepository.getClient(client.getCpf())} returns client
            every { stockRepository.getStock(updatedStock.getId())} returns null

            // when / then
            assertThrows(NoSuchElementException::class.java){
                stockService.updateStock(updatedStock)
            }
        }

        @Test
        @DisplayName("should throw NoSuchElementException when client owner of stock does not exist")
        fun verifyIncorrectStockUpdatedBecauseOfClient() {
            // given
            val client = Client("000.000.000-00","Fulano Ciclano",  "1999-1-1", "Santa Rita", "fulano@email.com.br", true, "fulano")
            val updatedStock = Stock(11111, 30, "Arabic", 20.0, "ALLOWED",client.getCpf())

            every { clientRepository.getClient(client.getCpf())} returns null
            every { stockRepository.getStock(updatedStock.getId())} returns updatedStock

            // when / then
            assertThrows(NoSuchElementException::class.java){
                stockService.updateStock(updatedStock)
            }
        }
    }

    @Nested
    @DisplayName("Test scenario for UPDATE STOCK STATUS")
    inner class UpdateStockStatus{

        @Test
        @DisplayName("should provide the updated stock")
        fun verifyCorrectStockUpdated() {
            // given
            val stockId = _stocks.get(2).getId()
            val stockStatus = "ALLOWED"

            every { stockRepository.getStock(stockId)} returns _stocks.get(2)
            every { stockRepository.updateStockStatus(stockId, stockStatus)} returns true

            // when
            val updatedStock = stockService.updateStockStatus(stockId, stockStatus)

            // then
            assertEquals(_stocks.get(2), updatedStock)
        }

        @Test
        @DisplayName("should throw NoSuchElementException when doesn't find stock with desired id")
        fun verifyIncorrectStockUpdated() {
            // given
            val stockId = _stocks.get(2).getId()
            val stockStatus = "ALLOWED"

            every { stockRepository.getStock(stockId)} returns null

            // when / then
            assertThrows(NoSuchElementException::class.java){
                stockService.updateStockStatus(stockId, stockStatus)
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
            val stock = Stock(22222, 40, "Arabic", 30.0, "REFUSED", "999.999.999-99")
            val targetStockId = stock.getId()
            val expectedValue = Unit

            every { stockRepository.getStock(targetStockId) } returns stock
            every { stockRepository.deleteStock(targetStockId) } returns true

            // when
            val returnedValue = stockService.deleteStock(targetStockId)

            // then
            assertEquals(expectedValue, returnedValue)
        }

        @Test
        @DisplayName("should throw NoSuchElementException when doesn't find stock with desired id")
        fun verifyIncorrectStockDeleted() {
            // given
            val stock = Stock(99999, 40, "Arabic", 30.0, "REFUSED", "999.999.999-99")
            val targetStockId = stock.getId()
            val expectedValue = Unit

            every { stockRepository.getStock(targetStockId) } returns null

            // when/then
            assertThrows(NoSuchElementException::class.java){
                stockService.deleteStock(targetStockId)
            }
        }
    }
}
