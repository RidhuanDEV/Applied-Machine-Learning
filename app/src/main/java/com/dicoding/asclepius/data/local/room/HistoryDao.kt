package com.dicoding.asclepius.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.asclepius.data.local.entity.HistoryEntity

@Dao
interface HistoryDao {
    @Query("SELECT * FROM History ORDER BY id ASC")
    fun getHistory(): LiveData<List<HistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertHistory(event: HistoryEntity)

    @Delete
    fun deleteHistory(event: HistoryEntity)

}