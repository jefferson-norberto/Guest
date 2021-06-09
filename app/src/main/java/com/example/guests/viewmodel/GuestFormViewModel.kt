package com.example.guests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.guests.service.model.GuestModel
import com.example.guests.service.repository.GuestRepository

class GuestFormViewModel(application: Application): AndroidViewModel(application) {
    private val mContext = application.applicationContext
    private val mGuestRepository: GuestRepository = GuestRepository.getInstance(mContext)

    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSaveGuest

    /**
     * Salva convidado
     * */
    fun save(name: String, phone: String, presence: Boolean) {
        val guest = GuestModel(name = name, phone = phone, presence = presence)
            mSaveGuest.value = mGuestRepository.save(guest)
    }
}