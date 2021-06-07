package com.example.guests.service.repository

import com.example.guests.service.model.GuestModel

class GuestRepository {

    fun getAllGuests(): List<GuestModel>{
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getPresents(): List<GuestModel>{
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getAbsents(): List<GuestModel>{
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    //CRUD
    fun save(guest: GuestModel){

    }

    fun update(guest: GuestModel){

    }

    fun remove(guest: GuestModel){

    }
}