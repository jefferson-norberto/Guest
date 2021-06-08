package com.example.guests.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guests.service.model.GuestModel
import com.example.guests.service.repository.GuestRepository

class GuestFormViewModel(application: Application): AndroidViewModel(application) {
    private val mContext = application.applicationContext
    private var mGuestRepository = GuestRepository.getInstance(mContext)

    private var mSaveGuest = MutableLiveData<Boolean>()
    val salveGuest = mSaveGuest

    fun save(name: String, phone: String, presence: Boolean){
        //estou criando o modelo do meu convidado passando os parametros acima
        val guest = GuestModel(name = name, phone = phone, presence = presence)
        mGuestRepository.save(guest)
    }
}