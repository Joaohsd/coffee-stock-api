package com.inatel.coffeestock.model.service

import com.inatel.coffeestock.model.entity.Client
import com.inatel.coffeestock.model.repository.interfaces.ClientRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class ClientService (
    @Qualifier("client") private val clientRepository: ClientRepository
){
    fun getClients() : Collection<Client> = clientRepository.getClients()

    fun getClient(cpf : String) : Client? = clientRepository.getClient(cpf)

    fun createClient(newClient : Client) : Client? = clientRepository.createClient(newClient)

    fun updateClient(updatedClient : Client) : Client? = clientRepository.updateClient(updatedClient)

    fun deleteClient(cpf : String) : Unit = clientRepository.deleteClient(cpf)
}