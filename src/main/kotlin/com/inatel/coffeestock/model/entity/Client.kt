package com.inatel.coffeestock.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDate

data class Client(
    private val _id : Long,
    private val _name: String,
    private val _cpf : String,
    private val _birthDate: LocalDate,
    private val _estate: String,
    private val _email: String,
    private val _password: String
){
    fun getId() : Long = _id

    fun getName() : String = _name

    fun getCpf() : String = _cpf

    fun getBirthDate() : LocalDate = _birthDate

    fun getEstate() : String  = _estate

    fun getEmail() : String = _email

    @JsonIgnore
    fun getPassword() : String = _password
}