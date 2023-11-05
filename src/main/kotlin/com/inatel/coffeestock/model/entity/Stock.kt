package com.inatel.coffeestock.model.entity

data class Stock (
    private val _id : Long,
    private val _quantity: Int,
    private val _coffeeType: String,
    private val _coffeeCupping: Double,
    private val _clientId : Long
){
    fun getId() : Long = _id

    fun getQuantity() : Int = _quantity

    fun getCoffeeType() : String = _coffeeType

    fun getCoffeeCupping() : Double = _coffeeCupping

    fun getClientId() : Long  = _clientId
}