package com.inatel.coffeestock.controller

import com.inatel.coffeestock.model.entity.Stock
import com.inatel.coffeestock.model.service.StockService
import com.inatel.coffeestock.utils.exception.ElementAlreadyExistsException
import org.springframework.core.annotation.AliasFor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/stocks")
class StockController (private val stockService: StockService){

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e : NoSuchElementException) : ResponseEntity<String> =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message);

    @ExceptionHandler(ElementAlreadyExistsException::class)
    fun handleBadRequest(e : ElementAlreadyExistsException) : ResponseEntity<String> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message);


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getStocks() : Collection<Stock> = stockService.getStocks();

    @GetMapping("/{stockID}")
    @ResponseStatus(HttpStatus.OK)
    fun getStock(@PathVariable stockID: Long) = stockService.getStock(stockID);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addStock(@RequestBody stock: Stock) : Stock = stockService.createStock(stock);

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateStock(@RequestBody stock: Stock) : Stock = stockService.updateStock(stock);

    @DeleteMapping("/{stockID}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteStock(@PathVariable stockID: Long) : Unit = stockService.deleteStock(stockID);

}