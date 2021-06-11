package com.example.guests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.guests.service.model.GuestModel
import com.example.guests.service.repository.GuestRepository

class GuestFormViewModel(application: Application): AndroidViewModel(application) {
    private val mContext = application.applicationContext
    private val mGuestRepository: GuestRepository = GuestRepository(mContext)

    //variável que escuta na hora de salvar
    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSaveGuest

    //varialveis usadas na hora de carregar o contato para editar
    private var mGuest = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = mGuest

    /**
     * Salva convidado
     * */
    fun save(id: Int, name: String, phone: String, presence: Boolean) {
        //usando o ROOM
        val guest = GuestModel().apply {
            this.id = id
            this.name = name
            this.phone = phone
            this.presence = presence
        }

        if(id == 0){
            mSaveGuest.value = mGuestRepository.save(guest)
        }else{
            mSaveGuest.value = mGuestRepository.update(guest)
        }

    }

    fun load(id: Int){
        //função já criada no repositorio que carrega um convidado pelo id
        mGuest.value = mGuestRepository.getOneGuest(id)
    }
}