package com.example.chargingalarm.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import com.example.chargingalarm.service.AlarmService

class ChargingReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Intent.ACTION_POWER_CONNECTED -> startService(context, AlarmService.ACTION_CHARGING_CONNECTED)
            Intent.ACTION_POWER_DISCONNECTED -> startService(context, AlarmService.ACTION_CHARGING_DISCONNECTED)
            Intent.ACTION_BATTERY_CHANGED -> {
                val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                val pct = if (level >= 0 && scale > 0) ((level * 100f) / scale).toInt() else -1
                startService(context, AlarmService.ACTION_UPDATE_NOTIFICATION, pct)
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
}
