package com.dicoding.asclepius.data.local.di

import android.content.Context
import com.dicoding.asclepius.data.HistoryRepository
import com.dicoding.asclepius.data.local.room.HistoryDatabase
import com.dicoding.asclepius.data.retrofit.ApiConfig
import com.dicoding.asclepius.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): HistoryRepository {
        val apiService = ApiConfig.getApiService()
        val database = HistoryDatabase.getInstance(context)
        val dao = database.historyDao()
        val appExecutors = AppExecutors()
        return HistoryRepository.getInstance(apiService, dao, appExecutors)
    }
}