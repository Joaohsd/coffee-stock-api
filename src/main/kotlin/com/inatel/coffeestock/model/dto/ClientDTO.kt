package com.inatel.coffeestock.model.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class ClientDTO (
    @field:NotBlank
    @field:Size(min = 14, max = 14, message = "CPF: Out of length (min:14 and max:14 characters).")
    private val cpf : String,
    @field:NotBlank
    private val name: String,
    @field:NotBlank
    private val birthDate: String,
    @field:NotBlank
    private val estate: String,
    @field:NotBlank
    private val email: String,
    @field:NotNull
    private val isAdmin: Boolean,
    @field:NotBlank
    private val password: String
){
    fun getName() : String = name

    fun getCpf() : String = cpf

    fun getBirthDate() : String = birthDate

    fun getEstate() : String  = estate

    fun getEmail() : String = email

    fun getPassword() : String = password

    fun isAdmin() : Boolean = isAdmin
}