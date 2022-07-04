package com.martin.mvvm.domain

import com.martin.mvvm.data.QuoteRepository
import com.martin.mvvm.data.database.entities.toDatabase
import com.martin.mvvm.data.model.QuoteModel
import com.martin.mvvm.domain.model.Quote
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(private val repository : QuoteRepository) {

    suspend operator fun invoke():List<Quote>{        //invoke sirve para que al llamar a GetQuotesUseCase pueda llamarse solo con el nombre sin instanciar
        val quotes = repository.getAllQuotesFromApi()

        return if(quotes.isNotEmpty()){
            repository.clearQuotes()
            repository.insertQuotes(quotes.map{ it.toDatabase() })
            quotes
        }else{
            repository.getAllQuotesFromDatabase()
        }
    }
}