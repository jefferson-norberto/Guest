package com.example.guests.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guests.service.model.GuestModel
import com.example.guests.service.repository.GuestRepository

class GuestFormViewModel: ViewModel() {
    private var mGuestRepository = GuestRepository()

    private var mSaveGuest = MutableLiveData<Boolean>()
    val salveGuest = mSaveGuest

    fun save(name: String, phone: String, presence: Boolean){
        val guest = GuestModel(name, phone, presence)
        mGuestRepository.save(guest)
    }
}