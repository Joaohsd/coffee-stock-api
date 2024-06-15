package com.inatel.coffeestock.model.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class StockDTO(
    @field:NotNull
    @Schema(name = "Stock id", example = "1", accessMode = Schema.AccessMode.READ_ONLY,
            required = false)
    private var id: Int,
    @field:NotNull
    @Schema(name = "Stock quantity", example = "10", required = true)
    private var quantity: Int,
    @field:NotBlank
    @Schema(name = "Stock type", example = "Arabic", required = true)
    private var coffeeType: String,
    @field:NotNull
    @Schema(name = "Stock cupping", example = "20", required = true)
    private var coffeeCupping: Double,
    @field:NotBlank
    @Schema(name = "Stock status", example = "Available", required = true)
    private var status: String,
    @field:NotBlank
    @Schema(name = "Stock owner CPF", example = "123.456.789-10", required = true)
    @field:Size(min = 14, max = 14, message = "CPF: Out of length (min:14 and max:14 characters).")
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