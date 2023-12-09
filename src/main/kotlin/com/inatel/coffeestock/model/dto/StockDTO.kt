package com.inatel.coffeestock.model.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class StockDTO(
    @NotNull private var id: Int,
    @NotNull private var quantity: Int,
    @NotBlank private var coffeeType: String,
    @NotNull private var coffeeCupping: Double,
    @NotBlank private var status: String,
    @NotBlank
    @Size(min = 14, max = 14, message = "CPF: Out of length (min:11 and max:11 characters).")
    private var clientCpf : String
) {
    fun getId() : Int = id

    fun getQuantity() : Int = quantity

    fun getCoffeeType() : String = coffeeType

    fun getCoffeeCupping() : Double = coffeeCupping

    fun getClientCpf() : String  = clientCpf

    fun getStatus() : String  = status

    // Setters for all properties
    fun setId(value: Int) {
        quantity = value
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