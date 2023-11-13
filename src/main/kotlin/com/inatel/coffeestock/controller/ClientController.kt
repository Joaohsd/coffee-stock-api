package com.inatel.coffeestock.controller

import com.inatel.coffeestock.model.entity.Client
import com.inatel.coffeestock.model.service.ClientService
import com.inatel.coffeestock.utils.exception.ElementAlreadyExistsException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/clients")
class ClientController(private val clientService: ClientService) {
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e : NoSuchElementException) : ResponseEntity<String> =
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message);

    @ExceptionHandler(ElementAlreadyExistsException::class)
    fun handleBadRequest(e : ElementAlreadyExistsException) : ResponseEntity<String> =
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message);


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getClients() : Collection<Client> = clientService.getClients();

    @GetMapping("/{clientID}")
    @ResponseStatus(HttpStatus.OK)
    fun getClient(@PathVariable clientID: Long) = clientService.getClient(clientID);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addClient(@RequestBody client: Client) : Client = clientService.createClient(client);

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateClient(@RequestBody client: Client) : Client = clientService.updateClient(client);

    @DeleteMapping("/{clientID}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteClient(@PathVariable clientID: Long) : Unit = clientService.deleteClient(clientID);


}