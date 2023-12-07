package com.inatel.coffeestock.model.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class ClientDTO (
    @NotBlank
    @Size(min = 11, max = 11, message = "CPF: Out of length (min:11 and max:11 characters).")
    private val cpf : String,
    @NotBlank
    private val name: String,
    @NotBlank
    private val birthDate: String,
    @NotBlank
    private val estate: String,
    @NotBlank
    private val email: String,
    @NotNull
    private val isAdmin: Boolean,
    @NotBlank
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