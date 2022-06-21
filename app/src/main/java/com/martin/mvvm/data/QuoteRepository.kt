package com.martin.mvvm.data

import com.martin.mvvm.data.model.QuoteModel
import com.martin.mvvm.data.model.QuoteProvider
import com.martin.mvvm.data.network.QuoteService

class QuoteRepository {

    private val api = QuoteService()

    suspend fun getAllQuotes():List<QuoteModel>{
        val response = api.getQuotes()
        QuoteProvider.quotes = response
        return response
    }
}