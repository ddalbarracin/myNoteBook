package com.utn.mcrarv1.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "accounts")
class useraccount (id: Int, nic : String, nombre: String, apellido : String, email : String, password : String) {

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id : Int = 1

    @ColumnInfo(name = "nic")
    var nic : String

    @ColumnInfo(name = "nombre")
    var nombre : String

    @ColumnInfo(name = "apellido")
    var apellido : String

    @ColumnInfo(name = "email")
    var email : String

    @ColumnInfo(name = "password")
    var password : String

    init {
        this.id = id
        this.nic = nic
        this.nombre = nombre
        this.apellido = apellido
        this.email = email
        this.password = password
    }

}