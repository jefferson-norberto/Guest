package com.example.guests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guests.service.model.GuestModel
import com.example.guests.service.repository.GuestRepository

//O viewModel tem que extenter AndroidViewModel e receber como parametro application do tipo Application
class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    //acesso aos dados
    private val mGuestRepository = GuestRepository.getInstance(application.applicationContext)

    private val mGuestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = mGuestList

    fun load(){
        //carregamos do repositorio e atribuimos a nossa GuestList
        mGuestList.value = mGuestRepository.getAllGuests()
    }
}