package com.inatel.coffeestock.controller

import com.inatel.coffeestock.model.dto.StockDTO
import com.inatel.coffeestock.model.entity.Stock
import com.inatel.coffeestock.model.service.StockService
import com.inatel.coffeestock.utils.exception.ElementAlreadyExistsException
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

    @GetMapping
    fun getStocks() : ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.OK).body(stockService.getStocks())
    }

    @GetMapping("/{stockId}")
    fun getStock(@PathVariable stockId: Int) : ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.OK).body(stockService.getStock(stockId))
    }

    @PostMapping
    fun addStock(@RequestBody @Validated stockDTO: StockDTO) : ResponseEntity<Any> {
        var stockToBeAdded = Stock()

        BeanUtils.copyProperties(stockDTO, stockToBeAdded)

        val stockAdded = stockService.createStock(stockToBeAdded)

        return ResponseEntity.status(HttpStatus.CREATED).body(stockAdded);
    }

    @PutMapping("/{stockId}/status/{stockStatus}")
    fun updateStockStatus(@PathVariable stockId: Int, @PathVariable stockStatus: String) : ResponseEntity<Any> {
        val stockUpdated = stockService.updateStockStatus(stockId, stockStatus)

        return ResponseEntity.status(HttpStatus.OK).body(stockUpdated);
    }
    @PutMapping
    fun updateStockStatus(@RequestBody @Validated stockDTO: StockDTO) : ResponseEntity<Any> {
        var stockToBeUpdated = Stock()

        BeanUtils.copyProperties(stockDTO, stockToBeUpdated)

        val stockUpdated = stockService.updateStock(stockToBeUpdated)

        return ResponseEntity.status(HttpStatus.OK).body(stockUpdated);
    }

    @DeleteMapping("/{stockId}")
    fun deleteStock(@PathVariable stockId: Int) : ResponseEntity<Any> {
        val stockDeleted = stockService.deleteStock(stockId)

        return ResponseEntity.status(HttpStatus.OK).body(stockDeleted);
    }

}