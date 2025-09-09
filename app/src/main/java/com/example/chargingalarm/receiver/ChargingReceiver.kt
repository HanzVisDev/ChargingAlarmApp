package com.example.chargingalarm.receiver

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
                if (pct >= 100) {
                    logChargingEvent(context, intent, "FULL_CHARGE")
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
                
                val temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1) / 10f
                val voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1)
                
                val history = ChargingHistory(
                    batteryLevel = pct,
                    isCharging = isCharging,
                    temperature = temperature,
                    voltage = voltage,
                    eventType = eventType,
                    timestamp = Date()
                )
                
                val database = AppDatabase.getDatabase(context)
                database.chargingHistoryDao().insertHistory(history)
            } catch (e: Exception) {
                android.util.Log.e("ChargingReceiver", "Error logging charging event", e)
            }
        }
    }
}

