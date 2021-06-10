/**
 * Interface usada para escutar a ação do click na minha lista de convidados
 * dessa forma mantemos o padrão
 * **/

package com.example.guests.view.listener

interface GuestListener {
    fun onClick(id: Int)
    fun onDelete(id: Int)
}