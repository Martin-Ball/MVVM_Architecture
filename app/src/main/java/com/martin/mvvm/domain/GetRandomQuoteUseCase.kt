package com.martin.mvvm.domain

import com.martin.mvvm.data.QuoteRepository
import com.martin.mvvm.data.model.QuoteModel
import com.martin.mvvm.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(private val repository: QuoteRepository) {

    suspend operator fun invoke(): Quote?{
        val quotes = repository.getAllQuotesFromDatabase()
        if(!quotes.isNullOrEmpty()){
            val randomNumber = (quotes.indices).random()
            return quotes[randomNumber]
        }
        return null
    }
}