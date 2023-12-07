package com.inatel.coffeestock.model.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class ClientDTO (
    @NotBlank
    @Size(min = 11, max = 11, message = "CPF: Out of length (min:11 and max:11 characters).")
    val _cpf : String,
    @NotBlank
    val _name: String,
    @NotBlank
    val _birthDate: String,
    @NotBlank
    val _estate: String,
    @NotBlank
    val _email: String,
    @NotNull
    val _isAdmin: Boolean,
){
}