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

    fun getOneGuest(id: Int): GuestModel?{

        //dessa forma digo que a variável pode ser nula
        var guest: GuestModel? = null
        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            //aqui é o critério de seleção no DB
            //neste caso uso o ID como criterio para pesquisar
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            //criando a variavel para passar as colunas na hora de selecionar
            val projection = arrayOf(DataBaseConstants.GUEST.COLUMNS.NAME,
                                     DataBaseConstants.GUEST.COLUMNS.PHONE,
                                     DataBaseConstants.GUEST.COLUMNS.PRESENCE)

            //Variavel usada para percorrer pelos dados no DB
            val cursor = db.query(
                                DataBaseConstants.GUEST.TABLE_NAME,
                                projection,
                                selection,
                                args,
                        null, //groupBy - agrupar por uma determinada condição
                         null, //having
                        null) //orderBy

            //verifico se ele é diferente de nulo
            if(cursor != null && cursor.count > 0){
                //movo o cursor para a primeira posição
                cursor.moveToFirst()

                val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                val phone = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PHONE))
                // para transformar o presence em boolean é só igualar essa atribuição a 1
                val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                guest = GuestModel(id, name, phone, presence)

            }
            //liberando o cursor no final
            cursor?.close()
            guest
        }catch (e: Exception){
            guest
        }
    }

    fun getAllGuests(): List<GuestModel>{
        val list: MutableList<GuestModel> = ArrayList()

        return try {
            val db = mGuestDataBaseHelper.readableDatabase


            //criando a variavel para passar as colunas na hora de selecionar
            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PHONE,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE)

            //Variavel usada para percorrer pelos dados no DB
            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                projection,
                null, //como quero pegar todos não preciso ter criterio de seleção
                null, //nem preciso ter argumentos
                null, //groupBy - agrupar por uma determinada condição
                null, //having
                null) //orderBy

            //verifico se ele é diferente de nulo
            if(cursor != null && cursor.count > 0){
                //preenchendo a lista de convidados para retornar
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val phone = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PHONE))
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, phone, presence)
                    list.add(guest)
                }
            }
            //liberando o cursor no final
            cursor?.close()
            list
        }catch (e: Exception){
            list
        }
    }

    fun getPresents(): List<GuestModel>{
        val list: MutableList<GuestModel> = ArrayList()

        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            //tem a mesma função do guestAll, mas é menos seguro pois é mais fácil de errar os nomes e argumentos
            val cursor = db.rawQuery("SELECT id, name, phone, presence FROM Guest WHERE presence = 1", null)

            //verifico se ele é diferente de nulo
            if(cursor != null && cursor.count > 0){
                //preenchendo a lista de convidados para retornar
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val phone = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PHONE))
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, phone, presence)
                    list.add(guest)
                }
            }
            //liberando o cursor no final
            cursor?.close()
            list
        }catch (e: Exception){
            list
        }
    }

    fun getAbsents(): List<GuestModel>{
        val list: MutableList<GuestModel> = ArrayList()

        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            //Variavel usada para percorrer pelos dados no DB
            val cursor = db.rawQuery("SELECT id, name, phone, presence FROM Guest WHERE presence = 0", null)

            //verifico se ele é diferente de nulo
            if(cursor != null && cursor.count > 0){
                //preenchendo a lista de convidados para retornar
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val phone = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PHONE))
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, phone, presence)
                    list.add(guest)
                }
            }
            //liberando o cursor no final
            cursor?.close()
            list
        }catch (e: Exception){
            list
        }
    }

    //Singleton - só tem uma instancia da classe repository
    fun save(guest: GuestModel): Boolean{
        //inserção no banco de dados, com um try catch para validar a operação
        try {
            //criando a conexão com o DB
            val db = mGuestDataBaseHelper.writableDatabase
            var contentValues = ContentValues()

            //o contentValues são os valores que desejo passar para tabela
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PHONE, guest.phone)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, contentValues)
            return true
        }catch (e: Exception){
            return false
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