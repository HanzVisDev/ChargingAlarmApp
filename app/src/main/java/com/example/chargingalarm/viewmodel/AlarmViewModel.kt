package com.example.chargingalarm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.chargingalarm.datastore.UserPreferencesRepository
import com.example.chargingalarm.data.ChargingHistory
import com.example.chargingalarm.data.AppDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AlarmState(
    val activeAlarmsCount: Int = 0,
    val fullChargeAlarmEnabled: Boolean = false,
    val chargeEndAlarmEnabled: Boolean = false,
    val restrictedLimitAlarmEnabled: Boolean = false,
    val restrictedLimitPercentage: Int = 80
)

class AlarmViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext
    private val userPrefsRepository = UserPreferencesRepository(context)
    private val database = AppDatabase.getDatabase(context)
    
    private val _alarmState = MutableStateFlow(AlarmState())
    val alarmState: StateFlow<AlarmState> = _alarmState.asStateFlow()
    
    init {
        observePreferences()
    }
    
    private fun observePreferences() {
        viewModelScope.launch {
            userPrefsRepository.prefsFlow.collect { prefs ->
                val activeCount = listOf(
                    prefs.fullChargeAlarmEnabled,
                    prefs.chargeEndAlarmEnabled,
                    prefs.restrictedLimitAlarmEnabled
                ).count { it }
                
                _alarmState.value = AlarmState(
                    activeAlarmsCount = activeCount,
                    fullChargeAlarmEnabled = prefs.fullChargeAlarmEnabled,
                    chargeEndAlarmEnabled = prefs.chargeEndAlarmEnabled,
                    restrictedLimitAlarmEnabled = prefs.restrictedLimitAlarmEnabled,
                    restrictedLimitPercentage = prefs.restrictedLimitPercentage
                )
            }
        }
    }
    
    fun checkAlarms(batteryPct: Int, isCharging: Boolean, wasCharging: Boolean) {
        viewModelScope.launch {
            val prefs = userPrefsRepository.prefsFlow.value
            
            // Check for full charge alarm
            if (prefs.fullChargeAlarmEnabled && batteryPct >= 100 && isCharging) {
                triggerAlarm("FULL_CHARGE", prefs.fullChargeToneUri, prefs.vibrateOnAlarm)
                logChargingEvent(batteryPct, isCharging, "FULL_CHARGE")
            }
            
            // Check for charge end alarm
            if (prefs.chargeEndAlarmEnabled && wasCharging && !isCharging) {
                triggerAlarm("CHARGE_END", prefs.chargeEndToneUri, prefs.vibrateOnAlarm)
                logChargingEvent(batteryPct, isCharging, "DISCONNECTED")
            }
            
            // Check for restricted limit alarm
            if (prefs.restrictedLimitAlarmEnabled && batteryPct >= prefs.restrictedLimitPercentage && isCharging) {
                triggerAlarm("RESTRICTED_LIMIT", prefs.restrictedLimitToneUri, prefs.vibrateOnAlarm)
                logChargingEvent(batteryPct, isCharging, "RESTRICTED_LIMIT")
            }
        }
    }
    
    private fun triggerAlarm(alarmType: String, toneUri: android.net.Uri?, vibrate: Boolean) {
        // This would integrate with the AlarmService
        // For now, we'll just log the event
        android.util.Log.d("AlarmViewModel", "Triggering alarm: $alarmType")
    }
    
    private suspend fun logChargingEvent(batteryPct: Int, isCharging: Boolean, eventType: String) {
        val history = ChargingHistory(
            batteryLevel = batteryPct,
            isCharging = isCharging,
            eventType = eventType
        )
        database.chargingHistoryDao().insertHistory(history)
    }
}
