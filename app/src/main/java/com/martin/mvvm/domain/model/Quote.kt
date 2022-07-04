package com.martin.mvvm.domain.model

import com.martin.mvvm.data.database.entities.QuoteEntity
import com.martin.mvvm.data.model.QuoteModel

data class Quote(val quote:String, val author:String)

fun QuoteModel.toDomain() = Quote(quote, author)

fun QuoteEntity.toDomain() = Quote(quote, author)