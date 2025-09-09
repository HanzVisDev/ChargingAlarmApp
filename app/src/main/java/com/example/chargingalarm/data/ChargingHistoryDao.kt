package com.example.chargingalarm.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ChargingHistoryDao {
    @Query("SELECT * FROM charging_history ORDER BY timestamp DESC LIMIT :limit")
    fun getRecentHistory(limit: Int = 10): Flow<List<ChargingHistory>>
    
    @Query("SELECT * FROM charging_history WHERE eventType = :eventType ORDER BY timestamp DESC")
    fun getHistoryByEventType(eventType: String): Flow<List<ChargingHistory>>
    
    @Query("SELECT * FROM charging_history WHERE timestamp >= :startDate ORDER BY timestamp DESC")
    fun getHistorySince(startDate: Long): Flow<List<ChargingHistory>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: ChargingHistory)
    
    @Delete
    suspend fun deleteHistory(history: ChargingHistory)
    
    @Query("DELETE FROM charging_history WHERE timestamp < :cutoffDate")
    suspend fun deleteOldHistory(cutoffDate: Long)
}
