package com.martin.mvvm.domain

import com.martin.mvvm.data.QuoteRepository
import com.martin.mvvm.data.model.QuoteModel
import com.martin.mvvm.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetQuotesUseCaseTest{

    @RelaxedMockK
    private lateinit var repository : QuoteRepository
    lateinit var getQuotesUseCase: GetQuotesUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getQuotesUseCase = GetQuotesUseCase(repository)
    }

    @Test
    fun whenTheApiDoesntReturnAnythingThenGetValuesFromDatabase() = runBlocking {
        //Given
        coEvery { repository.getAllQuotesFromApi() } returns emptyList() //siempre que en la funcion haya una corrutina es con coEvery, en caso contrario Every

        //When
        getQuotesUseCase()

        //Then
        coVerify(exactly = 1) { repository.getAllQuotesFromDatabase() }  //verificar
    }

    @Test
    fun whenTheApiReturnSomethingThenGetValuesFromApi() = runBlocking{
        //Given
        val listQuotes = listOf(Quote("Profi", "sampaio"));
        coEvery { repository.getAllQuotesFromApi() } returns listQuotes

        //When
        val response = getQuotesUseCase()

        //Then
        coVerify (exactly = 1) { repository.clearQuotes() }
        coVerify (exactly = 1) { repository.insertQuotes(any()) }
        coVerify (exactly = 0){ repository.getAllQuotesFromDatabase() }
        assert(listQuotes == response)

    }


}