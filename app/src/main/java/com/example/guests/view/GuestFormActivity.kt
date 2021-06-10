package com.example.guests.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.guests.viewmodel.GuestFormViewModel
import com.example.guests.R
import com.example.guests.service.constants.GuestConstants
import kotlinx.android.synthetic.main.activity_guest_form.*

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mViewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        //função que recebe os parâmetros passados pela outra activity
        loadData()

        //função de ouvir os botões
        setListeners()

        //função que usa o observer do modelo MVVM
        observe()

    }

    override fun onClick(v: View?) {
        val id = v!!.id
        if(id == R.id.button_save){

            val name = edit_name.text.toString()
            val phone = edit_phone.text.toString()
            val presence = rbutton_present.isChecked

            mViewModel.save(name, phone, presence)
        }
    }

    //carregamento dos dados da activity
    private fun loadData(){
        //já existe uma variável intent pois ela faz parte da activity do appCompatActivity
        //como passei na fragment pelo bundle eu recupero pelo bundle
        val bundle = intent.extras

        //verificando se é uma chamada nova ou se é uma edição do usuário
        if(bundle != null){
            val id = bundle.getInt(GuestConstants.GUESTID)

            //carregar esse contato no ViewModel
            mViewModel.load(id)
        }
    }

    private fun observe(){
        //observer usado para olhar na hora de salvar
        mViewModel.saveGuest.observe(this, Observer {
            if(it){
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_SHORT).show()
            }
            finish()
        })

        //o It aqui é do GuestModel pois na minha ViewModel informei esse tipo de parametro
        //aqui é a resposta para quando o usuário é carregado
        mViewModel.guest.observe(this, Observer {
            edit_name.setText(it.name)
            edit_phone.setText(it.phone)
            if(it.presence){
                rbutton_present.isChecked = true
            }else{
                rbutton_ausente.isChecked = true
            }
        })
    }

    private fun setListeners(){
        button_save.setOnClickListener(this)
    }
}