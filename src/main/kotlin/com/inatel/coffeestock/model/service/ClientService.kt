package com.inatel.coffeestock.model.service

import com.inatel.coffeestock.model.entity.Client
import com.inatel.coffeestock.model.repository.interfaces.ClientRepository
import com.inatel.coffeestock.utils.exception.ElementAlreadyExistsException
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.rmi.UnexpectedException

@Service
class ClientService (
    @Qualifier("client") private val clientRepository: ClientRepository
){
    fun getClients() : Collection<Client> = clientRepository.getClients()

    fun getClient(cpf : String) : Client? {
        return clientRepository.getClient(cpf)
                ?: throw NoSuchElementException("Client with given ${cpf} does not exist")
    }

    fun createClient(newClient : Client) : Client? {
        if(clientRepository.getClient(newClient.getCpf()) == null){
            return clientRepository.createClient(newClient)
        }
        else{
            throw ElementAlreadyExistsException("Client with given ${newClient.getCpf()} already exists")
        }
    }

    fun updateClient(updatedClient : Client) : Client? {
        if(clientRepository.getClient(updatedClient.getCpf()) == null){
            throw NoSuchElementException("Client with given ${updatedClient.getCpf()} does not exist")
        }
        else{
            return clientRepository.updateClient(updatedClient)
        }
    }

    fun deleteClient(cpf : String) : Unit {
        if(clientRepository.getClient(cpf) == null){
            throw NoSuchElementException("Client with given ${cpf} does not exist")
        }
        else{
            if (clientRepository.deleteClient(cpf))
                return Unit
        }
    }
}