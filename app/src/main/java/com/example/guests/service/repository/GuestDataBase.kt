package com.example.guests.service.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.guests.service.model.GuestModel

@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDataBase : RoomDatabase(){

    //Interfaces e abstratcs são todas usadas pelo ROOM
    abstract fun guestDAO(): GuestDAO

    companion object{
        private lateinit var INSTANCE: GuestDataBase
        fun getDataBase(context: Context): GuestDataBase{
            if (!::INSTANCE.isInitialized){
                //OBS 1
                synchronized(GuestDataBase::class){
                    INSTANCE = Room.databaseBuilder(context, GuestDataBase::class.java, "guestDB")
                        //OBS 2
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}

/**OBS 1
 * Com o synchronized é verificado se essa conecção está sendo usada
 * em mais de um núcleo, dessa forma torna-se mais seguro e não da
 * erro, já que os processos ficam sincronizados. É uma forma de prevenção.
 * **/

/** OBS 2
 * sem o allowMainthreadQueries não é possível fazer consultas no DB,
 * pois o room não deixa usar a mesma thread do aplicativo já que
 * operações em DB são custosas e dessa forma torna-se mais segura
 * **/