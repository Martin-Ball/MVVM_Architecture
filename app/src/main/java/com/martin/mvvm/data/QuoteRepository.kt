package com.martin.mvvm.data

import com.martin.mvvm.data.model.QuoteModel
import com.martin.mvvm.data.model.QuoteProvider
import com.martin.mvvm.data.network.QuoteService
import javax.inject.Inject

class QuoteRepository @Inject constructor(private val api : QuoteService, private val quoteProvider:QuoteProvider) {

    suspend fun getAllQuotes():List<QuoteModel>{
        val response = api.getQuotes()
        quoteProvider.quotes = response
        return response
    }
}