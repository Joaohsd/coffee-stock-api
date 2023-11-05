package com.inatel.coffeestock.model.service

import com.inatel.coffeestock.model.entity.Client
import com.inatel.coffeestock.model.repository.ClientRepository
import org.springframework.stereotype.Service

@Service
class ClientService (
    private val clientRepository: ClientRepository
){
    fun getClients() : Collection<Client> = clientRepository.getClients()

    fun getClient(id : Long) : Client = clientRepository.getClient(id)

    fun createClient(newClient : Client) : Client = clientRepository.createClient(newClient)

    fun updateClient(updatedClient : Client) : Client = clientRepository.updateClient(updatedClient)

    fun deleteClient(id : Long) : Client = clientRepository.deleteClient(id)
}