package com.utn.mcrarv1.database
import androidx.room.*

import com.google.android.play.core.install.model.UpdateAvailability
import com.utn.mcrarv1.entities.useraccount
@Dao
public  interface accountDao {

    @Query("SELECT * FROM accounts ORDER BY id")
    fun loadAllPersons(): MutableList<useraccount?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insertPerson(useraccount: useraccount?)

    @Update
    fun updatePerson(useraccount: useraccount?)

    @Delete
    fun deletePerson(useraccount: useraccount?)

    @Query("SELECT * FROM accounts WHERE id = :id")
    fun loadPersonById(id: Int): useraccount?
}