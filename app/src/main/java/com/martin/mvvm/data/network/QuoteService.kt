package com.martin.mvvm.data.network

import android.util.Log
import com.martin.mvvm.core.RetrofitHelper
import com.martin.mvvm.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuoteService @Inject constructor(private val api:QuoteApiClient) {

    suspend fun getQuotes(): List<QuoteModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getAllQuotes()
                response.body() ?: emptyList()
            } catch (e: Exception) {
                Log.d("ERROR:", e.toString())
                emptyList()
            }
        }
    }
}