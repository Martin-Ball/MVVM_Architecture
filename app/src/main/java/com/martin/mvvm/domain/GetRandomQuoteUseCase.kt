package com.martin.mvvm.domain

import com.martin.mvvm.data.QuoteRepository
import com.martin.mvvm.data.model.QuoteModel
import com.martin.mvvm.data.model.QuoteProvider
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor() {

    operator fun invoke():QuoteModel?{
        val quotes : List<QuoteModel> = QuoteProvider.quotes
        if(!quotes.isNullOrEmpty()){
            val randomNumber = (quotes.indices).random()
            return quotes[randomNumber]
        }
        return null
    }
}