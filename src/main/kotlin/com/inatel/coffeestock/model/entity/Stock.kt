package com.inatel.coffeestock.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore

data class Stock (
    private val _id : Long,
    private val _quantity: Int,
    private val _coffeeType: String,
    private val _coffeeCupping: Double,
    private val _status: String,
    @JsonIgnore private val _clientCpf : String
){
    fun getId() : Long = _id

    fun getQuantity() : Int = _quantity

    fun getCoffeeType() : String = _coffeeType

    fun getCoffeeCupping() : Double = _coffeeCupping

    fun getClientId() : String  = _clientCpf

    fun getStatus() : String  = _status
}