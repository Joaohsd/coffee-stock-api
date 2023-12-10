package com.inatel.coffeestock.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDate

data class Client(
    private var cpf : String = "",
    private var name: String = "",
    private var birthDate: String = "",
    private var estate: String = "",
    private var email: String = "",
    private var isAdmin: Boolean = false,
    private var password: String = ""
){

    fun getName() : String = name

    fun getCpf() : String = cpf

    fun getBirthDate() : String = birthDate

    fun getEstate() : String  = estate

    fun getEmail() : String = email

    fun getPassword() : String = password

    fun isAdmin() : Boolean = isAdmin

    // Setters for all properties
    fun setCpf(value: String) {
        cpf = value
    }

    fun setName(value: String) {
        name = value
    }

    fun setBirthDate(value: String) {
        birthDate = value
    }

    fun setEstate(value: String) {
        estate = value
    }

    fun setEmail(value: String) {
        email = value
    }

    fun setIsAdmin(value: Boolean) {
        isAdmin = value
    }

    fun setPassword(value: String) {
        password = value
    }
}