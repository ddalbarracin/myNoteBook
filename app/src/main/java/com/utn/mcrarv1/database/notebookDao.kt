package com.utn.mcrarv1.database

import androidx.room.*
import com.utn.mcrarv1.entities.notebook
@Dao
public interface notebookDao {

    @Query("SELECT * FROM notebooks ORDER BY id")
    fun loadContact(): MutableList<notebook?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insertContact(notebook: notebook?)

    @Update
    fun updateContact(notebook: notebook?)

    @Delete
    fun deleteContact(notebook: notebook?)

    @Query("SELECT * FROM notebooks WHERE id = :id")
    fun loadContactById(id: Int): notebook?

    @Query("SELECT * FROM notebooks WHERE userID = :userID")
    fun loadContactByUserId(userID: Int): MutableList<notebook?>?

    @Query("SELECT * FROM notebooks WHERE userID = :userID AND id = :id")
    fun loadContactByUserIDContactID (userID: Int, id: Int): notebook?

}