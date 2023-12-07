package com.inatel.coffeestock.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDate

data class Client(
    private val _cpf : String,
    private val _name: String,
    private val _birthDate: String,
    private val _estate: String,
    private val _email: String,
    private val _isAdmin: Boolean,
    private val _password: String
){
    fun getName() : String = _name

    fun getCpf() : String = _cpf

    fun getBirthDate() : String = _birthDate

    fun getEstate() : String  = _estate

    fun getEmail() : String = _email

    fun getPassword() : String = _password

    fun isAdmin() : Boolean = _isAdmin
}