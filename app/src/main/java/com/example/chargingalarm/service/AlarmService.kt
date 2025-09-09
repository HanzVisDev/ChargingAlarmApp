package com.example.chargingalarm.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.app.NotificationCompat
import com.example.chargingalarm.R
import com.example.chargingalarm.datastore.UserPreferencesRepository
import com.example.chargingalarm.ui.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AlarmService : Service() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var prefs: UserPreferencesRepository

    private var mediaPlayer: MediaPlayer? = null
    private var isForegroundStarted = false

    override fun onCreate() {
        super.onCreate()
        prefs = UserPreferencesRepository(applicationContext)
        createChannels()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_CHARGING_CONNECTED -> onChargingConnected()
            ACTION_CHARGING_DISCONNECTED -> onChargingDisconnected()
            ACTION_MUTE -> mute()
            ACTION_UNMUTE -> unmute()
            ACTION_UPDATE_NOTIFICATION -> updateNotification(intent.getIntExtra(EXTRA_BATTERY_PCT, -1))
        }
        if (!isForegroundStarted) {
            startForeground(NOTIF_ID, buildNotification(-1, null))
            isForegroundStarted = true
        }
        return START_STICKY
    }

    private fun onChargingConnected() {
        scope.launch {
            val p = prefs.prefsFlow.first()
            if (!p.alarmMuted && p.playOnChargeStart) {
                triggerAlarm(p.alarmToneUri, p.vibrateOnAlarm)
            }
            updateNotification(-1, true)
        }
    }

    private fun onChargingDisconnected() {
        scope.launch {
            val p = prefs.prefsFlow.first()
            if (!p.alarmMuted && p.playOnChargeStop) {
                triggerAlarm(p.alarmToneUri, p.vibrateOnAlarm)
            }
            updateNotification(-1, false)
        }
    }

    private fun triggerAlarm(alarmUri: Uri?, vibrate: Boolean) {
        stopAlarm()
        mediaPlayer = MediaPlayer().apply {
            val uri = alarmUri ?: defaultAlarm()
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            setDataSource(applicationContext, uri)
            isLooping = false
            prepare()
            start()
        }
        if (vibrate) vibrateOnce()
    }

    private fun stopAlarm() {
        mediaPlayer?.run { stop(); release() }
        mediaPlayer = null
    }

    private fun vibrateOnce() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            @Suppress("DEPRECATION") vibrator.vibrate(500)
        }
    }

    private fun mute() { scope.launch { prefs.setAlarmMuted(true); updateNotification(-1, null) }; stopAlarm() }
    private fun unmute() { scope.launch { prefs.setAlarmMuted(false); updateNotification(-1, null) } }

    private fun defaultAlarm(): Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

    private fun createChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(CHANNEL_ID, "Charging Alarm", NotificationManager.IMPORTANCE_LOW)
            nm.createNotificationChannel(channel)
        }
    }

    private fun buildNotification(batteryPct: Int, isCharging: Boolean?): Notification {
        val openApp = PendingIntent.getActivity(
            this, 1, Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val mutePending = PendingIntent.getService(this, 2, Intent(this, AlarmService::class.java).apply { action = ACTION_MUTE }, PendingIntent.FLAG_IMMUTABLE)
        val unmutePending = PendingIntent.getService(this, 3, Intent(this, AlarmService::class.java).apply { action = ACTION_UNMUTE }, PendingIntent.FLAG_IMMUTABLE)

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_battery)
            .setContentTitle("Charging Alarm")
            .setContentText(buildString {
                append("Battery ")
                if (batteryPct >= 0) append("$batteryPct% ")
                if (isCharging != null) append(if (isCharging) "Charging" else "Not charging")
            })
            .setOngoing(true)
            .setOnlyAlertOnce(true)
            .setContentIntent(openApp)
            .addAction(R.drawable.ic_mute, "Mute", mutePending)
            .addAction(R.drawable.ic_unmute, "Unmute", unmutePending)
            .build()
    }

    private fun updateNotification(batteryPct: Int, isCharging: Boolean? = null) {
        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notif = buildNotification(batteryPct, isCharging)
        if (isForegroundStarted) nm.notify(NOTIF_ID, notif) else startForeground(NOTIF_ID, notif)
    }

    override fun onDestroy() { stopAlarm(); super.onDestroy() }
    override fun onBind(intent: Intent?): IBinder? = null

    companion object {
        const val CHANNEL_ID = "charging_alarm_channel"
        const val NOTIF_ID = 101
        const val ACTION_CHARGING_CONNECTED = "com.example.chargingalarm.ACTION_CHARGING_CONNECTED"
        const val ACTION_CHARGING_DISCONNECTED = "com.example.chargingalarm.ACTION_CHARGING_DISCONNECTED"
        const val ACTION_UPDATE_NOTIFICATION = "com.example.chargingalarm.ACTION_UPDATE_NOTIFICATION"
        const val ACTION_MUTE = "com.example.chargingalarm.ACTION_MUTE"
        const val ACTION_UNMUTE = "com.example.chargingalarm.ACTION_UNMUTE"
        const val EXTRA_BATTERY_PCT = "extra_battery_pct"
    }
}
