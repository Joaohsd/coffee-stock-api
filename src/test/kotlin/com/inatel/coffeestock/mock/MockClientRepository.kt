package com.inatel.coffeestock.mock

import com.inatel.coffeestock.model.entity.Client
import com.inatel.coffeestock.model.repository.ClientRepository
import com.inatel.coffeestock.utils.exception.ElementAlreadyExistsException
import java.time.LocalDate

class MockClientRepository:ClientRepository{
    private val clients = mutableListOf<Client>(
        Client(123456, "Fulano Ciclano", "123.456.789-10", LocalDate.of(1999, 1, 1), "Santa Rita", "fulano@email.com.br", "fulano"),
        Client(234567, "Ciclano Fulano", "999.999.999-99", LocalDate.of(1996, 2, 2), "Rancho Alegre", "ciclano@email.com.br", "ciclano"),
        Client(345678, "Federico José", "111.111.111-11", LocalDate.of(1980, 3, 3), "Boa Vista", "frederico@email.com.br", "frederico")
    )

    override fun getClients(): Collection<Client> = clients

    override fun getClient(id: Long): Client {
        return clients.firstOrNull{ it.getId() == id }
            ?: throw NoSuchElementException("Could not find a client with given ID $id")
    }

    override fun createClient(newClient: Client): Client{
        if (clients.any { it.getId() == newClient.getId() }){
            throw ElementAlreadyExistsException("Client with given ${newClient.getId()} already exists")
        }
        clients.add(newClient)

        return newClient
    }

    override fun updateClient(updatedClient: Client): Client {
        val clientToBeRemoved = clients.firstOrNull({it.getId() == updatedClient.getId()})
                ?: throw NoSuchElementException("Could not find a client with given ID ${updatedClient.getId()}")
        clients.remove(clientToBeRemoved)
        clients.add(updatedClient)
        return updatedClient
    }

    override fun deleteClient(id: Long): Unit{
        val clientToBeRemoved = clients.firstOrNull({it.getId() == id})
                ?: throw NoSuchElementException("Could not find a client with given ID ${id}")

        clients.remove(clientToBeRemoved)
        return Unit
    }

}