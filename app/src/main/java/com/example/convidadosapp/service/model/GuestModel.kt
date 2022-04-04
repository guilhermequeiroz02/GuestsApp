package com.example.convidadosapp.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Guest")
 class GuestModel{

     @PrimaryKey(autoGenerate = true)
     @ColumnInfo(name = "id")
     var id: Int = 0

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "presence")
    var presence: Boolean = true
    //val id: Int = 0, var name: String, var presence: Boolean

 }