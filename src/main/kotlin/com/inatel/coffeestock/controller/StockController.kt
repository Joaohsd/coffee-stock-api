package com.inatel.coffeestock.controller

import com.inatel.coffeestock.model.dto.StockDTO
import com.inatel.coffeestock.model.entity.Stock
import com.inatel.coffeestock.model.service.StockService
import com.inatel.coffeestock.utils.exception.ElementAlreadyExistsException
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "Stock Resource", description = "Resource to perform a full CRUD for Stock entity.")
@RequestMapping("/api/stocks")
class StockController (private val stockService: StockService){

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e : NoSuchElementException) : ResponseEntity<String> =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message);

    @ExceptionHandler(ElementAlreadyExistsException::class)
    fun handleBadRequest(e : ElementAlreadyExistsException) : ResponseEntity<String> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message);

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleForbiddenRequest(e : MethodArgumentNotValidException) : ResponseEntity<String> =
            ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.fieldError?.defaultMessage.toString());

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleForbiddenRequest(e : HttpMessageNotReadableException) : ResponseEntity<String> =
            ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.message);

    @ApiResponses(
            value = [
                ApiResponse(responseCode = "200", description = "Stocks retrieved successfully"),
                ApiResponse(responseCode = "404", description = "No stocks found")
            ]
    )
    @GetMapping
    fun getStocks() : ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.OK).body(stockService.getStocks())
    }

    @ApiResponses(
            value = [
                ApiResponse(responseCode = "200", description = "Stock retrieved successfully"),
                ApiResponse(responseCode = "404", description = "Stock not found")
            ]
    )
    @GetMapping("/{stockId}")
    fun getStock(@PathVariable stockId: Int) : ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.OK).body(stockService.getStock(stockId))
    }

    @ApiResponses(
            value = [
                ApiResponse(responseCode = "201", description = "Stock created successfully"),
                ApiResponse(responseCode = "400", description = "Stock already exists"),
                ApiResponse(responseCode = "403", description = "Invalid stock data")
            ]
    )
    @PostMapping
    fun addStock(@RequestBody @Validated stockDTO: StockDTO) : ResponseEntity<Any> {
        var stockToBeAdded = Stock()

        BeanUtils.copyProperties(stockDTO, stockToBeAdded)

        val stockAdded = stockService.createStock(stockToBeAdded)

        return ResponseEntity.status(HttpStatus.CREATED).body(stockAdded);
    }

    @ApiResponses(
            value = [
                ApiResponse(responseCode = "200", description = "Stock status updated successfully"),
                ApiResponse(responseCode = "404", description = "Stock not found"),
                ApiResponse(responseCode = "403", description = "Invalid stock data")
            ]
    )
    @PutMapping("/{stockId}/status/{stockStatus}")
    fun updateStockStatus(@PathVariable stockId: Int, @PathVariable stockStatus: String) : ResponseEntity<Any> {
        val stockUpdated = stockService.updateStockStatus(stockId, stockStatus)

        return ResponseEntity.status(HttpStatus.OK).body(stockUpdated);
    }

    @ApiResponses(
            value = [
                ApiResponse(responseCode = "200", description = "Stock updated successfully"),
                ApiResponse(responseCode = "404", description = "Stock not found"),
                ApiResponse(responseCode = "403", description = "Invalid stock data")
            ]
    )
    @PutMapping
    fun updateStockStatus(@RequestBody @Validated stockDTO: StockDTO) : ResponseEntity<Any> {
        var stockToBeUpdated = Stock()

        BeanUtils.copyProperties(stockDTO, stockToBeUpdated)

        val stockUpdated = stockService.updateStock(stockToBeUpdated)

        return ResponseEntity.status(HttpStatus.OK).body(stockUpdated);
    }

    @ApiResponses(
            value = [
                ApiResponse(responseCode = "200", description = "Stock deleted successfully"),
                ApiResponse(responseCode = "404", description = "Stock not found")
            ]
    )
    @DeleteMapping("/{stockId}")
    fun deleteStock(@PathVariable stockId: Int) : ResponseEntity<Any> {
        val stockDeleted = stockService.deleteStock(stockId)

        return ResponseEntity.status(HttpStatus.OK).body(stockDeleted);
    }

}