package com.example.chargingalarm.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "charging_history")
data class ChargingHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val batteryLevel: Int,
    val isCharging: Boolean,
    val powerLevel: Int? = null,
    val temperature: Float? = null,
    val voltage: Int? = null,
    val timestamp: Long = System.currentTimeMillis(),
    val eventType: String // "CONNECTED", "DISCONNECTED", "LEVEL_CHANGE", "FULL_CHARGE"
)
