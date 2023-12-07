package com.inatel.coffeestock.controller

import com.inatel.coffeestock.model.dto.ClientDTO
import com.inatel.coffeestock.model.entity.Client
import com.inatel.coffeestock.model.service.ClientService
import com.inatel.coffeestock.model.service.StockService
import com.inatel.coffeestock.utils.exception.ElementAlreadyExistsException
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.beans.BeanUtils
import org.springframework.boot.autoconfigure.integration.IntegrationProperties
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


@RestController
@Tag(name = "Client Resource", description = "Resource to perform a full CRUD for Client entity.")
@RequestMapping("/api/clients")
class ClientController(private val clientService: ClientService, private val stockService: StockService) {
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e : NoSuchElementException) : ResponseEntity<String> =
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message);

    @ExceptionHandler(ElementAlreadyExistsException::class)
    fun handleBadRequest(e : ElementAlreadyExistsException) : ResponseEntity<String> =
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message);


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getClients() : ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getClients())
    }

    @GetMapping("/{clientCpf}")
    @ResponseStatus(HttpStatus.OK)
    fun getClient(@PathVariable clientCpf: String) : ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getClient(clientCpf))
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addClient(@RequestBody @Valid clientDTO: ClientDTO) : ResponseEntity<Any> {
        var clientToBeAdded = Client()

        BeanUtils.copyProperties(clientDTO, clientToBeAdded)

        val clientAdded = clientService.createClient(clientToBeAdded)

        return ResponseEntity.status(HttpStatus.CREATED).body(clientAdded);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateClient(@RequestBody clientDTO: ClientDTO) : ResponseEntity<Any> {
        var clientToBeUpdated = Client()

        BeanUtils.copyProperties(clientDTO, clientToBeUpdated)

        val clientUpdated = clientService.updateClient(clientToBeUpdated)

        return ResponseEntity.status(HttpStatus.OK).body(clientUpdated);
    }

    @DeleteMapping("/{clientCpf}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteClient(@PathVariable clientCpf: String) : ResponseEntity<Any> {
        val clientDeleted = clientService.deleteClient(clientCpf)

        return ResponseEntity.status(HttpStatus.OK).body(clientDeleted);
    }

    @GetMapping("/{clientCpf}/stocks")
    @ResponseStatus(HttpStatus.OK)
    fun getStocksFromClient(@PathVariable clientCpf: String) : ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.OK).body(stockService.getStocksFromClient(clientCpf))
    }

}