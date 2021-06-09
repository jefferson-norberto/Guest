package com.example.guests.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.guests.viewmodel.GuestFormViewModel
import com.example.guests.R
import kotlinx.android.synthetic.main.activity_guest_form.*

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mViewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()

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

    private fun observe(){
        mViewModel.saveGuest.observe(this, Observer {
            if(it){
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_SHORT).show()
            }
            finish()
        })
    }

    private fun setListeners(){
        button_save.setOnClickListener(this)
    }
}