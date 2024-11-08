package com.dicoding.asclepius.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.asclepius.data.HistoryRepository
import com.dicoding.asclepius.data.Result
import com.dicoding.asclepius.data.local.entity.HistoryEntity
import com.dicoding.asclepius.data.response.Article
import com.dicoding.asclepius.data.response.HistoryResponse
import com.dicoding.asclepius.data.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryViewModel(private val historyRepository: HistoryRepository): ViewModel() {

    private val _eventDetail = MutableLiveData<Result<List<Article>>>()
    val eventDetail: LiveData<Result<List<Article>>> = _eventDetail
    fun getHistory() = historyRepository.getHistory()

    fun deleteHistory(history: HistoryEntity) {
        viewModelScope.launch {
            historyRepository.deleteHistory(history)
        }
    }
    fun insertHistory(history: HistoryEntity) {
        viewModelScope.launch {
            historyRepository.insertHistory(history)
        }
    }
    fun fetchNews() {
        _eventDetail.postValue(Result.Loading)

        val client = ApiConfig.getApiService().getNews()
        client.enqueue(object : Callback<HistoryResponse> {
            override fun onResponse(call: Call<HistoryResponse>, response: Response<HistoryResponse>) {
                val event = response.body()?.articles
                if (response.isSuccessful && event != null) {
                    _eventDetail.postValue(Result.Success(event))
                } else {
                    _eventDetail.postValue(Result.Error("Failed to get event detail: ${response.message()}"))
                }
            }

            override fun onFailure(call: Call<HistoryResponse>, t: Throwable) {
                _eventDetail.postValue(Result.Error("Error: ${t.message}"))
            }
        })
    }

}