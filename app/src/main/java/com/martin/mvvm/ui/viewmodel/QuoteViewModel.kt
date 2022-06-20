package com.martin.mvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.martin.mvvm.model.QuoteModel
import com.martin.mvvm.model.QuoteProvider

class QuoteViewModel : ViewModel() {
//LiveData permite al activiry (ui) suscribirse a un modelo y si hay cambios en el puede saberlo

    val quoteModel = MutableLiveData<QuoteModel>()  //el valor de adentro va a ir cambiando

    fun randomQuote(){
        val currentQuote = QuoteProvider.random()
        quoteModel.postValue(currentQuote)
    }

}