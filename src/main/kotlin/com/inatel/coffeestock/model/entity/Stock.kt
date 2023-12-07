package com.inatel.coffeestock.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore

data class Stock (
    private var id : Int = -1,
    private var quantity: Int = -1,
    private var coffeeType: String = "",
    private var coffeeCupping: Double = -1.0,
    private var status: String = "",
    private var clientCpf : String = ""
){
    fun getId() : Int = id

    fun getQuantity() : Int = quantity

    fun getCoffeeType() : String = coffeeType

    fun getCoffeeCupping() : Double = coffeeCupping

    fun getClientCpf() : String  = clientCpf

    fun getStatus() : String  = status

    // Setters for all properties
    fun setId(value: Int) {
        id = value
    }

    fun setQuantity(value: Int) {
        quantity = value
    }

    fun setCoffeeType(value: String) {
        coffeeType = value
    }

    fun setCoffeeCupping(value: Double) {
        coffeeCupping = value
    }

    fun setStatus(value: String) {
        status = value
    }

    fun setClientCpf(value: String) {
        clientCpf = value
    }
}