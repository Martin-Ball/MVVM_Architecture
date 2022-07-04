package com.martin.mvvm.data.database.dao

import androidx.room.*
import com.martin.mvvm.data.database.entities.QuoteEntity

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quote_table ORDER BY author DESC")
    suspend fun getAllQuotes():List<QuoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes:List<QuoteEntity>)

    @Query("DELETE FROM quote_table")
    suspend fun deleteAllQuotes()
}