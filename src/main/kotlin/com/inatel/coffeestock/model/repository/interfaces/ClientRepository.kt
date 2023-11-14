package com.inatel.coffeestock.model.repository.interfaces

import com.inatel.coffeestock.model.entity.Client

interface ClientRepository {
    fun getClients() : Collection<Client>

    fun getClient(id:Long) : Client

    fun createClient(newClient:Client) : Client

    fun updateClient(updatedClient:Client) : Client

    fun deleteClient(id:Long) : Unit
}