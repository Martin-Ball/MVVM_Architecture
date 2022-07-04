package com.martin.mvvm.data

import com.martin.mvvm.data.database.dao.QuoteDao
import com.martin.mvvm.data.database.entities.QuoteEntity
import com.martin.mvvm.data.model.QuoteModel
import com.martin.mvvm.data.network.QuoteService
import com.martin.mvvm.domain.model.Quote
import com.martin.mvvm.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api : QuoteService,
    private val quoteDao: QuoteDao) {

    suspend fun getAllQuotesFromApi():List<Quote>{
        val response:List<QuoteModel> = api.getQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun getAllQuotesFromDatabase():List<Quote>{
        val response = quoteDao.getAllQuotes()
        return response.map{ it.toDomain() }
    }

    suspend fun insertQuotes(quotes:List<QuoteEntity>){
        quoteDao.insertAll(quotes)
    }

    suspend fun clearQuotes(){
        quoteDao.deleteAllQuotes()
    }
}