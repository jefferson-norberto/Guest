package com.example.guests.service.repository

import android.content.Context
import com.example.guests.service.model.GuestModel

class GuestRepository private constructor(context: Context){

    private var mGuestDataBaseHelper = GuestDataBaseHelper(context)

    companion object{
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository{
            if(!::repository.isInitialized){
                repository = GuestRepository(context)
            }
            return repository
        }
    }

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

    //Singleton - só tem uma instancia da classe repository
    fun save(guest: GuestModel){

    }

    fun update(guest: GuestModel){

    }

    fun remove(guest: GuestModel){

    }
}