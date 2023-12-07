package com.inatel.coffeestock.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDate

data class Client(
    private val cpf : String,
    private val name: String,
    private val birthDate: String,
    private val estate: String,
    private val email: String,
    private val isAdmin: Boolean,
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