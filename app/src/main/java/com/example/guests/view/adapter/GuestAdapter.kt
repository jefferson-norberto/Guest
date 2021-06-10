/**
 * A RecyclerView se torna mais performática que o ListView
 * Pois o onCreate é chamado apenas 1 vez para preencher a tela
 * Depois disso ela fica somente no onBinder
 * **/

package com.example.guests.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.guests.R
import com.example.guests.service.model.GuestModel
import com.example.guests.view.listener.GuestListener
import com.example.guests.view.viewholder.GuestViewHolder

class GuestAdapter: RecyclerView.Adapter<GuestViewHolder>() {

    //variavel usada para autalizar e contar os elementos
    private var mGuestList: List<GuestModel> = arrayListOf()
    private lateinit var mGuestListener: GuestListener

    //para implementar esse método é preciso de um layout específico para ele
    //neste caso foi cirado um layout chamado rwo_guest
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        //o parent dele é o recyclerView
        //item é do tipo View
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_guest, parent, false)
        return GuestViewHolder(item, mGuestListener)
    }

    //eu pego os elementos do layout e atribuo os valores
    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        //criei um método no GuestViewHolder
        holder.bind(mGuestList[position])
    }

    //retorna o numero de elementos
    override fun getItemCount(): Int {
        return mGuestList.count()
    }

    fun updateGuests(list: List<GuestModel>){
        //atualizando a minha lista
        mGuestList = list
        notifyDataSetChanged()
    }

    //estou preenchendo o meu GuestListener no AllGuestFragment
    //o adapter recebe do fragmente e passa para viewholder
    fun attachListener(listener: GuestListener){
        mGuestListener = listener
    }
}