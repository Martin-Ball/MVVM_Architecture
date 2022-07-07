package com.martin.mvvm.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.martin.mvvm.domain.GetQuotesUseCase
import com.martin.mvvm.domain.GetRandomQuoteUseCase
import com.martin.mvvm.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class QuoteViewModelTest{

    @RelaxedMockK
    private lateinit var getQuotesUseCase: GetQuotesUseCase

    @RelaxedMockK
    private lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    private lateinit var quoteViewModel: QuoteViewModel

    @get:Rule
    var rule:InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        quoteViewModel = QuoteViewModel(getQuotesUseCase, getRandomQuoteUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun whenViewModelIsCreatedAtTheFirstTimeGetAllQuotesAndSetTheFirstValue() = runTest{
        //Given
        val quoteList = listOf(Quote("Quote1", "Author1"), Quote("Quote2", "Author2"))
        coEvery { getQuotesUseCase() } returns quoteList

        //When
        quoteViewModel.onCreate()

        //Then
        assert(quoteViewModel.quoteModel.value == quoteList.first())
    }

    @Test
    fun whenCallRandomQuoteReturnAQuoteSetOnTheLiveData() = runTest{
        //Given
        val quoteList = Quote("EJEMPLO2", "ejemplo")
        coEvery { getRandomQuoteUseCase() } returns quoteList

        //When
        quoteViewModel.randomQuote()

        //Then
        assert(quoteViewModel.quoteModel.value == quoteList)
    }

    @Test
    fun ifRandomQuoteUseCaseReturnNullKeepTheLastValue() = runTest {
        //Given
        val quoteList = Quote("EJEMPLO2", "ejemplo")
        quoteViewModel.quoteModel.value = quoteList
        coEvery { getRandomQuoteUseCase() } returns null

        //When
        quoteViewModel.randomQuote()

        //Then
        assert( quoteViewModel.quoteModel.value == quoteList )

    }

}