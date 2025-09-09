package com.example.chargingalarm.viewmodel

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.chargingalarm.datastore.UserPreferencesRepository
import com.example.chargingalarm.datastore.UserPrefs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class BatteryState(
    val batteryPct: Int = 0,
    val isCharging: Boolean = false,
    val powerLevel: Int = 0, // Watts
    val temperature: Float = 0f, // Celsius
    val timeLeft: String = "Unknown", // Time left to charge
    val voltage: Int = 0, // Voltage in millivolts
    val health: Int = 0 // Battery health percentage
)

class BatteryViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext
    private val userPrefsRepository = UserPreferencesRepository(context)

    private val _batteryState = MutableStateFlow(BatteryState())
    val batteryState: StateFlow<BatteryState> = _batteryState.asStateFlow()

    val uiState: StateFlow<Pair<BatteryState, UserPrefs>> =
        combine(_batteryState, userPrefsRepository.prefsFlow) { b, p -> b to p }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), BatteryState() to UserPrefs())

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent == null) return
            when (intent.action) {
                Intent.ACTION_BATTERY_CHANGED -> updateFromBatteryChanged(intent)
                Intent.ACTION_POWER_CONNECTED -> setCharging(true)
                Intent.ACTION_POWER_DISCONNECTED -> setCharging(false)
            }
        }
    }

    init {
        register()
        // Initialize once with sticky battery intent
        updateFromBatteryChanged(context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED)))
    }

    private fun register() {
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_CHANGED)
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }
        context.registerReceiver(receiver, filter)
    }

    private fun updateFromBatteryChanged(intent: Intent?) {
        if (intent == null) return
        val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
        val isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL
        val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        val pct = if (level >= 0 && scale > 0) ((level * 100f) / scale).toInt() else 0
        
        // Get additional battery information
        val temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1) / 10f // Convert to Celsius
        val voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1)
        val health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1)
        
        // Calculate power level (approximate)
        val powerLevel = if (isCharging && voltage > 0) {
            // Rough estimation: voltage * current (estimated)
            (voltage * 0.001f * 2.5f).toInt() // Simplified calculation
        } else 0
        
        // Calculate time left (simplified)
        val timeLeft = calculateTimeLeft(pct, isCharging)
        
        _batteryState.value = _batteryState.value.copy(
            batteryPct = pct,
            isCharging = isCharging,
            powerLevel = powerLevel,
            temperature = temperature,
            timeLeft = timeLeft,
            voltage = voltage,
            health = health
        )
    }
    
    private fun calculateTimeLeft(batteryPct: Int, isCharging: Boolean): String {
        if (!isCharging || batteryPct >= 100) return "Unknown"
        
        // Simplified calculation - in real app, you'd track charging rate over time
        val remainingPct = 100 - batteryPct
        val estimatedHours = remainingPct / 20f // Assume ~20% per hour charging rate
        
        return when {
            estimatedHours < 1 -> "${(estimatedHours * 60).toInt()} min"
            estimatedHours < 24 -> "${estimatedHours.toInt()}h ${((estimatedHours % 1) * 60).toInt()}m"
            else -> "${(estimatedHours / 24).toInt()}d ${(estimatedHours % 24).toInt()}h"
        }
    }

    private fun setCharging(value: Boolean) {
        _batteryState.value = _batteryState.value.copy(isCharging = value)
    }

    fun setAlarmMuted(muted: Boolean) {
        viewModelScope.launch { userPrefsRepository.setAlarmMuted(muted) }
    }
}

