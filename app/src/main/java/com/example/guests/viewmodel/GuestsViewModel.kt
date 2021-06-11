package com.example.guests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guests.service.constants.GuestConstants
import com.example.guests.service.model.GuestModel
import com.example.guests.service.repository.GuestRepository

//O viewModel tem que extenter AndroidViewModel e receber como parametro application do tipo Application
class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    //acesso aos dados
    private val mGuestRepository = GuestRepository(application.applicationContext)

    private val mGuestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = mGuestList

    fun load(filter: Int){
        //carregamos do repositorio e atribuimos a nossa GuestList

        if(filter == GuestConstants.FILTER.EMPTY){
            mGuestList.value = mGuestRepository.getAllGuests()
        }else if(filter == GuestConstants.FILTER.PRESENT){
            mGuestList.value = mGuestRepository.getPresents()
        }else{
            mGuestList.value = mGuestRepository.getAbsents()
        }
    }

    //não preciso usar o mGuestList aqui pois o load já faz isso na chamada dentro do fragment
    fun delete(id: Int){
        val guest = mGuestRepository.getOneGuest(id)
        mGuestRepository.delete(guest)
    }
}