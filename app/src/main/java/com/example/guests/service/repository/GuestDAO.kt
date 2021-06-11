package com.example.guests.service.repository

import androidx.room.*
import com.example.guests.service.model.GuestModel

@Dao
interface GuestDAO {

    @Insert
    fun save(guest: GuestModel): Long

    @Update
    fun update(guest: GuestModel): Int

    @Delete
    fun remove(guest: GuestModel)

    @Query("SELECT * FROM Guest WHERE id = :id")
    fun getOneGuest(id: Int): GuestModel

    @Query("SELECT * FROM Guest")
    fun getAllGuests(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 1")
    fun getPresents(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = 0")
    fun getAbsents(): List<GuestModel>
}