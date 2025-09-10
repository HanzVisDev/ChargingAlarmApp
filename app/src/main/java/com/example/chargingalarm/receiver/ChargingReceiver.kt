package com.example.chargingalarm.receiver

import kotlinx.coroutines.flow.first

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import com.example.chargingalarm.service.AlarmService
import com.example.chargingalarm.data.AppDatabase
import com.example.chargingalarm.data.ChargingHistory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.Date

class ChargingReceiver : BroadcastReceiver() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Intent.ACTION_POWER_CONNECTED -> {
                startService(context, AlarmService.ACTION_CHARGING_CONNECTED)
                logChargingEvent(context, intent, "CONNECTED")
            }
            Intent.ACTION_POWER_DISCONNECTED -> {
                startService(context, AlarmService.ACTION_CHARGING_DISCONNECTED)
                logChargingEvent(context, intent, "DISCONNECTED")
            }
            Intent.ACTION_BATTERY_CHANGED -> {
                val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                val pct = if (level >= 0 && scale > 0) ((level * 100f) / scale).toInt() else -1
                startService(context, AlarmService.ACTION_UPDATE_NOTIFICATION, pct)
                
                // Check for full charge
                val prefs = com.example.chargingalarm.datastore.UserPreferencesRepository(context)
                scope.launch {
                    val p = prefs.prefsFlow.first()
                    if (pct >= 100 && p.fullChargeAlarmEnabled) {
                        logChargingEvent(context, intent, "FULL_CHARGE")
                        startService(context, AlarmService.ACTION_ALARM_FULL_CHARGE)
                    }
                    if (p.restrictedLimitAlarmEnabled && pct >= p.restrictedLimitPercentage) {
                        startService(context, AlarmService.ACTION_ALARM_RESTRICTED_LIMIT)
                    }
                }
            }
        }
    }

    private fun startService(context: Context, action: String, batteryPct: Int? = null) {
        val svc = Intent(context, AlarmService::class.java).apply {
            this.action = action
            if (batteryPct != null) putExtra(AlarmService.EXTRA_BATTERY_PCT, batteryPct)
        }
        context.startForegroundService(svc)
    }
    
    private fun logChargingEvent(context: Context, intent: Intent, eventType: String) {
        scope.launch {
            try {
                val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                val pct = if (level >= 0 && scale > 0) ((level * 100f) / scale).toInt() else 0
                
                val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
                val isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL
                
                val tempRaw = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, Int.MIN_VALUE)
                val temperature = if (tempRaw == Int.MIN_VALUE) readTemperatureFallback(context) else tempRaw / 10f
                val voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1)
                
                val history = ChargingHistory(
                    batteryLevel = pct,
                    isCharging = isCharging,
                    temperature = temperature,
                    voltage = voltage,
                    eventType = eventType,
                    timestamp = System.currentTimeMillis()
                )
                
                val database = AppDatabase.getDatabase(context)
                database.chargingHistoryDao().insertHistory(history)
            } catch (e: Exception) {
                android.util.Log.e("ChargingReceiver", "Error logging charging event", e)
            }
        }
    }
    
    private fun readTemperatureFallback(context: Context): Float {
        return try {
            val bm = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
            // Some devices expose BATTERY_PROPERTY_TEMPERATURE in tenths of a degree
            val tempTenths = bm.getIntProperty(5 /* BATTERY_PROPERTY_TEMPERATURE unofficial */)
            if (tempTenths != 0) tempTenths / 10f else Float.NaN
        } catch (_: Exception) {
            Float.NaN
        }
    }
}