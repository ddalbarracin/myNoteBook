package com.utn.mcrarv1.database

import android.content.Context
import androidx.room.Room
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.utn.mcrarv1.entities.notebook
import com.utn.mcrarv1.entities.useraccount

@Database(
    entities = [
        useraccount::class,
        notebook::class ],
    version = 2,
    exportSchema = false
)

public abstract class appdatabase : RoomDatabase() {


    abstract fun accountDao(): accountDao
    abstract fun notebookDao(): notebookDao

    companion object {

        var INSTANCE : appdatabase? = null

        fun getAppDataBase(context: Context): appdatabase? {

            if (INSTANCE == null) {
                synchronized(appdatabase::class) {
                    INSTANCE = databaseBuilder(

                        context.applicationContext,
                        appdatabase::class.java,
                        "myDB"

                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}


