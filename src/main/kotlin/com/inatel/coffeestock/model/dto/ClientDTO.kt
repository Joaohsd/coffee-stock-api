package com.inatel.coffeestock.model.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class ClientDTO (
    @field:NotBlank
    @Schema(name = "Client CPF", example = "123.456.789-10", required = true)
    @field:Size(min = 14, max = 14, message = "CPF: Out of length (min:14 and max:14 characters).")
    private val cpf : String,
    @field:NotBlank
    @Schema(name = "Client name", example = "Fulano Ciclano", required = true)
    private val name: String,
    @field:NotBlank
    @Schema(name = "Client birth date", example = "19-12-1940", required = true)
    private val birthDate: String,
    @field:NotBlank
    @Schema(name = "Client estate", example = "Farm Ville", required = true)
    private val estate: String,
    @field:NotBlank
    @Schema(name = "Client email", example = "fulano@email.com", required = true)
    private val email: String,
    @field:NotNull
    @Schema(name = "Client admin level", example = "false", required = true)
    private val isAdmin: Boolean,
    @field:NotBlank
    @Schema(name = "Client password", example = "fulanopassword", required = true)
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