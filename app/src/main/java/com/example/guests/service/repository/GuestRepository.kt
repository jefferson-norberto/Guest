package com.example.guests.service.repository

import android.content.ContentValues
import android.content.Context
import com.example.guests.service.constants.DataBaseConstants
import com.example.guests.service.model.GuestModel

class GuestRepository private constructor(context: Context){

    private var mGuestDataBaseHelper = GuestDataBaseHelper(context)

    companion object{
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository{
            if(!::repository.isInitialized){
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun getAllGuests(): List<GuestModel>{
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getPresents(): List<GuestModel>{
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getAbsents(): List<GuestModel>{
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    //Singleton - só tem uma instancia da classe repository
    fun save(guest: GuestModel): Boolean{
        //inserção no banco de dados, com um try catch para validar a operação
        return try {
            //criando a conexão com o DB
            val db = mGuestDataBaseHelper.writableDatabase
            var contentValues = ContentValues()

            //o contentValues são os valores que desejo passar para tabela
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PHONE, guest.phone)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, contentValues)
            true
        }catch (e: Exception){
            false
        }
    }

    fun update(guest: GuestModel): Boolean{
        return try {
            val db = mGuestDataBaseHelper.writableDatabase
            var contentValues = ContentValues()

            //o contentValues são os valores que desejo passar para tabela
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PHONE, guest.phone)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            //aqui é o critério de seleção no DB
            //neste caso uso o ID como criterio para pesquisar
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(guest.id.toString())

            //Buscando o convidado pelo id, passando, os valores, a seleção e depois os agumentos
            db.update(DataBaseConstants.GUEST.TABLE_NAME, contentValues, selection, args)
            true
        }catch (e: Exception){
            false
        }
    }

    //para deletar não preciso do convidado e sim somente do ID dele.
    fun delete(id: Int): Boolean{
        return try {
            //criando a conexão com o DB
            val db = mGuestDataBaseHelper.writableDatabase

            //aqui é o critério de seleção no DB
            //neste caso uso o ID como criterio para pesquisar
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            //Buscando o convidado pelo id, passando a seleção e depois o agumento
            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
            true
        }catch (e: Exception){
            false
        }
    }
}