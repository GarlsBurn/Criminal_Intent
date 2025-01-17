package com.bignerdranch.android.criminalintent.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bignerdranch.android.criminalintent.Crime
import java.util.UUID

@Dao
interface CrimeDao {
    @Query ("SELECT * FROM crime ")
    fun getCrimes(): LiveData<List<Crime>>
    @Query ("SELECT * FROM crime WHERE id=(:id)")
    suspend fun getCrimeById(id: UUID): Crime?

}