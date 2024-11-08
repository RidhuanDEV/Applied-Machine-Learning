package com.dicoding.asclepius.data

import androidx.lifecycle.LiveData
import com.dicoding.asclepius.data.local.entity.HistoryEntity
import com.dicoding.asclepius.data.local.room.HistoryDao
import com.dicoding.asclepius.data.retrofit.ApiService
import com.dicoding.asclepius.utils.AppExecutors

class HistoryRepository private constructor(
    private val apiService: ApiService,
    private val historyDao: HistoryDao,
    private val appExecutors: AppExecutors
) {

    fun insertHistory(history: HistoryEntity) {
        appExecutors.diskIO.execute {
            historyDao.insertHistory(history)
        }
    }
    fun getHistory(): LiveData<List<HistoryEntity>> {
        return historyDao.getHistory()
    }
    fun deleteHistory(history: HistoryEntity) {
        appExecutors.diskIO.execute {
            historyDao.deleteHistory(history)
        }
    }



    companion object {
        @Volatile
        private var instance: HistoryRepository? = null
        fun getInstance(
            apiService: ApiService,
            historyDao: HistoryDao,
            appExecutors: AppExecutors
        ): HistoryRepository =
            instance ?: synchronized(this) {
                instance ?: HistoryRepository(apiService, historyDao, appExecutors)
            }.also { instance = it }
    }
}