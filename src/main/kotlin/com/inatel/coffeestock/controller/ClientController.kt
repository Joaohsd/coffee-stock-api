package com.inatel.coffeestock.controller

import com.inatel.coffeestock.model.entity.Client
import com.inatel.coffeestock.model.service.ClientService
import com.inatel.coffeestock.utils.exception.ElementAlreadyExistsException
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@Tag(name = "Client Resource", description = "Resource to perform a full CRUD for Client entity.")
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

    @GetMapping("/{clientCpf}")
    @ResponseStatus(HttpStatus.OK)
    fun getClient(@PathVariable clientCpf: String) = clientService.getClient(clientCpf);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addClient(@RequestBody client: Client) : Client? {
        return clientService.createClient(client);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateClient(@RequestBody client: Client) : Client? = clientService.updateClient(client);

    @DeleteMapping("/{clientCpf}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteClient(@PathVariable clientCpf: String) : Unit = clientService.deleteClient(clientCpf);


}