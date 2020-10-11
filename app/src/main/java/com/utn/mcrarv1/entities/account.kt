package com.utn.mcrarv1.entities

import android.os.Parcel
import android.os.Parcelable

class account(nic : String, nombre: String, apellido : String, email : String, password : String, indice: Int) : Parcelable {

    var nic : String
    var nombre : String
    var apellido : String
    var email : String
    var password : String
    var indice : Int

    init {
        this.nic = nic
        this.nombre = nombre
        this.apellido = apellido
        this.email = email
        this.password = password
        this.indice = indice
    }


    constructor(source: Parcel) : this(
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(nic)
        writeString(nombre)
        writeString(apellido)
        writeString(email)
        writeString(password)
        writeInt(indice)
    }

    override fun toString(): String {
        return "account(nic='$nic', nombre='$nombre', apellido='$apellido', email='$email', password='$password', indice=$indice)"
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<account> = object : Parcelable.Creator<account> {
            override fun createFromParcel(source: Parcel): account = account(source)
            override fun newArray(size: Int): Array<account?> = arrayOfNulls(size)
        }
    }
}
