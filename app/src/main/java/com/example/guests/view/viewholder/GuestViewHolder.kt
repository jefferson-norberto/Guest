/**
 * Classe usada para o adapter do recycler para poder listar os convidados
 * ele guarda as referencias dos elementos de layout
 * **/

package com.example.guests.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guests.R
import com.example.guests.service.model.GuestModel

class GuestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(guest: GuestModel){
        //pegando a referencia do meu row_guest
        val textName = itemView.findViewById<TextView>(R.id.text_name_guest)
        val textPhone = itemView.findViewById<TextView>(R.id.text_phone_guest)
        //quando encontro atribuo o meu guest ao textView
        textName.text = guest.name
        textPhone.text = guest.phone


    }
}