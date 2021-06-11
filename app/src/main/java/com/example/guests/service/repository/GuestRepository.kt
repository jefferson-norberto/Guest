package com.example.guests.service.repository

import android.content.Context
import com.example.guests.service.model.GuestModel

class GuestRepository(context: Context){

    //Acesso ao Banco de dados
    private val mDataBase = GuestDataBase.getDataBase(context).guestDAO()

    fun getOneGuest(id: Int): GuestModel{
        return mDataBase.getOneGuest(id)
    }

    fun getAllGuests(): List<GuestModel>{
        return mDataBase.getAllGuests()
    }

    fun getPresents(): List<GuestModel>{
        return mDataBase.getPresents()
    }

    fun getAbsents(): List<GuestModel>{
        return mDataBase.getAbsents()
    }

    fun save(guest: GuestModel): Boolean{
        //OBS 1
        return mDataBase.save(guest) > 0
    }

    fun update(guest: GuestModel): Boolean{
        return mDataBase.update(guest) > 0
    }

    fun delete(guest: GuestModel){
        mDataBase.remove(guest)
    }
}

/**OBS 1
 * Como o save no GuestDataBase retorna um LONG
 * faz a verificação se esse LONG é maior que zero
 * satisfazendo minha função save local já que ela
 * retorna um Boolean
 * **/