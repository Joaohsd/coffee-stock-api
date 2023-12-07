package com.inatel.coffeestock.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore

data class Stock (
    private val id : Long,
    private val quantity: Int,
    private val coffeeType: String,
    private val coffeeCupping: Double,
    private val status: String,
    private val clientCpf : String
){
    fun getId() : Long = id

    fun getQuantity() : Int = quantity

    fun getCoffeeType() : String = coffeeType

    fun getCoffeeCupping() : Double = coffeeCupping

    fun getClientId() : String  = clientCpf

    fun getStatus() : String  = status
}