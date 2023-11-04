package com.inatel.coffeestock.model.repository

import com.inatel.coffeestock.model.entity.Client

interface ClientRepository {
    fun getClients() : Collection<Client>

    fun getClient(id:Long) : Client
}