package com.inatel.coffeestock.controller

import com.inatel.coffeestock.model.dto.ClientDTO
import com.inatel.coffeestock.model.entity.Client
import com.inatel.coffeestock.model.service.ClientService
import com.inatel.coffeestock.model.service.StockService
import com.inatel.coffeestock.utils.exception.ElementAlreadyExistsException
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.beans.BeanUtils
import org.springframework.boot.autoconfigure.integration.IntegrationProperties
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
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

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleForbiddenRequest(e : MethodArgumentNotValidException) : ResponseEntity<String> =
            ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.fieldError?.defaultMessage.toString());

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleForbiddenRequest(e : HttpMessageNotReadableException) : ResponseEntity<String> =
            ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.message);

    @ApiResponses(
            value = [
                ApiResponse(responseCode = "200", description = "Clients retrieved successfully"),
                ApiResponse(responseCode = "404", description = "No clients found")
            ]
    )
    @GetMapping
    fun getClients() : ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getClients())
    }

    @ApiResponses(
            value = [
                ApiResponse(responseCode = "200", description = "Client retrieved successfully"),
                ApiResponse(responseCode = "404", description = "Client not found")
            ]
    )
    @GetMapping("/{clientCpf}")
    fun getClient(@PathVariable clientCpf: String) : ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getClient(clientCpf))
    }

    @ApiResponses(
            value = [
                ApiResponse(responseCode = "201", description = "Client created successfully"),
                ApiResponse(responseCode = "400", description = "Client already exists"),
                ApiResponse(responseCode = "403", description = "Invalid client data")
            ]
    )
    @PostMapping
    fun addClient(@Valid @RequestBody clientDTO: ClientDTO) : ResponseEntity<Any> {
        var clientToBeAdded = Client()

        BeanUtils.copyProperties(clientDTO, clientToBeAdded)

        clientToBeAdded.setIsAdmin(clientDTO.isAdmin())

        val clientAdded = clientService.createClient(clientToBeAdded)

        return ResponseEntity.status(HttpStatus.CREATED).body(clientAdded);
    }

    @ApiResponses(
            value = [
                ApiResponse(responseCode = "200", description = "Client updated successfully"),
                ApiResponse(responseCode = "404", description = "Client not found"),
                ApiResponse(responseCode = "403", description = "Invalid client data")
            ]
    )
    @PutMapping
    fun updateClient(@Valid @RequestBody clientDTO: ClientDTO) : ResponseEntity<Any> {
        var clientToBeUpdated = Client()

        BeanUtils.copyProperties(clientDTO, clientToBeUpdated)

        clientToBeUpdated.setIsAdmin(clientDTO.isAdmin())

        val clientUpdated = clientService.updateClient(clientToBeUpdated)

        return ResponseEntity.status(HttpStatus.OK).body(clientUpdated);
    }

    @ApiResponses(
            value = [
                ApiResponse(responseCode = "200", description = "Client deleted successfully"),
                ApiResponse(responseCode = "404", description = "Client not found")
            ]
    )
    @DeleteMapping("/{clientCpf}")
    fun deleteClient(@PathVariable clientCpf: String) : ResponseEntity<Any> {
        val clientDeleted = clientService.deleteClient(clientCpf)

        return ResponseEntity.status(HttpStatus.OK).body(clientDeleted);
    }

    @ApiResponses(
            value = [
                ApiResponse(responseCode = "200", description = "Client stocks retrieved successfully"),
                ApiResponse(responseCode = "404", description = "Client or stocks not found")
            ]
    )
    @GetMapping("/{clientCpf}/stocks")
    fun getStocksFromClient(@PathVariable clientCpf: String) : ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.OK).body(stockService.getStocksFromClient(clientCpf))
    }

}