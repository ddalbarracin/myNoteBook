package com.utn.mcrarv1.entities
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "notebooks")
class notebook (id : Int, userID : Int, alias : String, nombre: String, apellido : String,
                telefono : String, email : String, direccion : String, trabajo: String, direccionlaboral:String,
                telefonolaboral:String, emaillaboral: String, imagen:String, notas:String ){


    @PrimaryKey
    @ColumnInfo(name = "id")
    var id : Int = 1

    @ColumnInfo(name = "userID")
    var userID : Int

    @ColumnInfo(name = "alias")
    var alias : String

    @ColumnInfo(name = "nombre")
    var nombre : String

    @ColumnInfo(name = "apellido")
    var apellido : String

    @ColumnInfo(name = "telefono")
    var telefono : String

    @ColumnInfo(name = "email")
    var email : String

    @ColumnInfo(name = "direccion")
    var direccion : String

    @ColumnInfo(name = "trabajo")
    var trabajo : String

    @ColumnInfo(name = "direccionlaboral")
    var direccionlaboral : String

    @ColumnInfo(name = "telefonolaboral")
    var telefonolaboral : String

    @ColumnInfo(name = "emaillaboral")
    var emaillaboral : String

    @ColumnInfo(name = "imagen")
    var imagen : String

    @ColumnInfo(name = "notas")
    var notas : String


    init {
        this.id = id
        this.userID = userID
        this.alias = alias
        this.nombre = nombre
        this.apellido = apellido
        this.telefono = telefono
        this.email = email
        this.direccion = direccion
        this.trabajo = trabajo
        this.direccionlaboral = direccionlaboral
        this.telefonolaboral = telefonolaboral
        this.emaillaboral = emaillaboral
        this.imagen = imagen
        this.notas = notas
    }



}
