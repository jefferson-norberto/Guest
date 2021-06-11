package com.example.guests.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.guests.service.constants.DataBaseConstants

//Entidade criada usando os padrões do ROOM
@Entity(tableName = "Guest")
class GuestModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name ="phone")
    var phone: String = ""

    @ColumnInfo(name = "presence")
    var presence: Boolean = true
}