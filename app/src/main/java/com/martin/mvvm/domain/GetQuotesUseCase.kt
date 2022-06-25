package com.martin.mvvm.domain

import com.martin.mvvm.data.QuoteRepository
import com.martin.mvvm.data.model.QuoteModel
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(private val repository : QuoteRepository) {

    suspend operator fun invoke():List<QuoteModel>?{        //invoke sirve para que al llamar a GetQuotesUseCase pueda llamarse solo con el nombre sin instanciar
        return repository.getAllQuotes()
    }
}