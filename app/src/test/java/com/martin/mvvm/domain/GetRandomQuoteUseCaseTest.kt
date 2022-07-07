package com.martin.mvvm.domain

import com.martin.mvvm.data.QuoteRepository
import com.martin.mvvm.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetRandomQuoteUseCaseTest{

    @RelaxedMockK
    private lateinit var repository: QuoteRepository
    private lateinit var getRandomQuotesUseCase : GetRandomQuoteUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getRandomQuotesUseCase = GetRandomQuoteUseCase(repository)
    }

    @Test
    fun whenTheApiReturnAnythingReturnNullQuotes() = runBlocking {
        //Given
        coEvery { repository.getAllQuotesFromDatabase() } returns emptyList()

        //When
        val response = getRandomQuotesUseCase()

        //Then
        assert( response == null )
    }

    @Test
    fun whenTheApiReturnAnythingReturnRandomQuote() = runBlocking {
        //Given
        val quoteList = listOf(Quote("profi", "samp"))
        coEvery { repository.getAllQuotesFromDatabase() } returns quoteList

        //When
        val response = getRandomQuotesUseCase()

        //Then
        assert(response == quoteList.first())
    }
}