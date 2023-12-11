package com.inatel.coffeestock.mock

import com.inatel.coffeestock.model.entity.Client
import com.inatel.coffeestock.model.repository.interfaces.ClientRepository
import com.inatel.coffeestock.utils.exception.ElementAlreadyExistsException
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository("mock_client")
class MockClientRepository: ClientRepository {
    private val clients = mutableListOf<Client>(
        Client("123.456.789-10","Fulano Ciclano",  "1999-1-1", "Santa Rita", "fulano@email.com.br", true, "fulano"),
        Client("999.999.999-99", "Ciclano Fulano",  "1996-2-2", "Rancho Alegre", "ciclano@email.com.br", false, "ciclano"),
        Client("111.111.111-11", "Frederico José",  "1980-3-3", "Boa Vista", "frederico@email.com.br", false, "frederico")
    )

    override fun getClients(): Collection<Client> = clients

    override fun getClient(cpf: String): Client? {
        return clients.firstOrNull{ it.getCpf() == cpf }
    }

    override fun createClient(newClient: Client): Client?{
        clients.add(newClient)
        return newClient
    }

    override fun updateClient(updatedClient: Client): Client? {
        clients.remove(clients.first { it.getCpf() == updatedClient.getCpf()})
        clients.add(updatedClient)
        return updatedClient
    }

    override fun deleteClient(cpf: String): Boolean{
        val clientToBeRemoved = clients.firstOrNull({it.getCpf() == cpf})
                ?:return false
        clients.remove(clientToBeRemoved)
        return true
    }

}