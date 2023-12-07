package com.inatel.coffeestock.model.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class StockDTO(
    @NotNull val _quantity: Int,
    @NotBlank val _coffeeType: String,
    @NotNull val _coffeeCupping: Double,
    @NotBlank val _status: String,
    @JsonIgnore @NotNull val _clientCpf : String
) {
}