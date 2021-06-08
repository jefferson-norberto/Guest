package com.example.guests.service.model

//classe usada como meu modelo de convidado
//para não precisar declarar o ID coloco ele como Default para o valor 0
data class GuestModel(val id: Int = 0, var name: String, var phone: String, var presence: Boolean ) {
}