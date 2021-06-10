/**
 * Classe usada para o adapter do recycler para poder listar os convidados
 * ele guarda as referencias dos elementos de layout
 * **/

package com.example.guests.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guests.R
import com.example.guests.service.model.GuestModel
import com.example.guests.view.listener.GuestListener

//o parametro listener é a classe que está ouvindo a função de onclick
class GuestViewHolder(itemView: View, private val listener: GuestListener) : RecyclerView.ViewHolder(itemView) {

    fun bind(guest: GuestModel){
        //pegando a referencia do meu row_guest
        val textName = itemView.findViewById<TextView>(R.id.text_name_guest)
        val textPhone = itemView.findViewById<TextView>(R.id.text_phone_guest)
        val checkPresence = itemView.findViewById<CheckBox>(R.id.check_presence)
        //quando encontro atribuo o meu guest ao textView
        textName.text = guest.name
        textPhone.text = guest.phone

        //como presence é um boolean não preciso de if else, ele já tem minha informação
        checkPresence.isChecked = guest.presence

        //faz o setOnClickListener do texName e invoca o onClick
        textName.setOnClickListener {
            //para fazer a atualização eu passo o id do convidado
            listener.onClick(guest.id)
        }

        textName.setOnLongClickListener {

            AlertDialog.Builder(itemView.context)
                .setTitle(R.string.remocao_convidado)
                .setMessage(R.string.deseja_remover)
                .setPositiveButton(R.string.remover){ dialog, which ->
                    listener.onDelete(guest.id)
                }
                .setNeutralButton(R.string.cancelar, null)
                .show()

            true
        }

    }
}