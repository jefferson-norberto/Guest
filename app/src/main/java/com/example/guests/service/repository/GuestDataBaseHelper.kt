package com.example.guests.service.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.guests.service.constants.DataBaseConstants

//A versão é necessária, pois caso seja adicionado uma nova coluna
//a pessoa que já usa o app e receba a atualização o android
//entenda que é preciso fazer uma atualização banco de dados
class GuestDataBaseHelper(context: Context): SQLiteOpenHelper(context, DATA_BASE_NAME, null, DATA_BASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_GUEST)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    companion object{
        private const val DATA_BASE_VERSION = 1
        private const val DATA_BASE_NAME = "Convidados.db"

        private const val CREATE_TABLE_GUEST =
            ("create table " + DataBaseConstants.GUEST.TABLE_NAME + " ("
                    + DataBaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement, "
                    + DataBaseConstants.GUEST.COLUMNS.NAME + " text, "
                    + DataBaseConstants.GUEST.COLUMNS.PHONE + " text, "
                    + DataBaseConstants.GUEST.COLUMNS.PRESENCE + " integer);")

    }

}