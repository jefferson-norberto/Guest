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

    //variável que escuta na hora de salvar
    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSaveGuest

    //varialveis usadas na hora de carregar o contato para editar
    private var mGuest = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = mGuest

    /**
     * Salva convidado
     * */
    fun save(name: String, phone: String, presence: Boolean) {
        val guest = GuestModel(name = name, phone = phone, presence = presence)
            mSaveGuest.value = mGuestRepository.save(guest)
    }

    fun load(id: Int){
        //função já criada no repositorio que carrega um convidado pelo id
        mGuest.value = mGuestRepository.getOneGuest(id)
    }
}