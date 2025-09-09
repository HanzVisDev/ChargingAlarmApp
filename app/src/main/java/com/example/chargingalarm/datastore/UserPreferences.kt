package com.example.chargingalarm.datastore

import android.content.Context
import android.net.Uri
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATASTORE_NAME = "user_preferences"

val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)

enum class ThemePreference { LIGHT, DARK, SYSTEM }

object PrefKeys {
    val PlayOnChargeStart = booleanPreferencesKey("play_on_charge_start")
    val PlayOnChargeStop = booleanPreferencesKey("play_on_charge_stop")
    val VibrateOnAlarm = booleanPreferencesKey("vibrate_on_alarm")
    val AlarmToneUri = stringPreferencesKey("alarm_tone_uri")
    val Theme = stringPreferencesKey("theme_preference")
    val AlarmMuted = booleanPreferencesKey("alarm_muted")
}

data class UserPrefs(
    val playOnChargeStart: Boolean = true,
    val playOnChargeStop: Boolean = false,
    val vibrateOnAlarm: Boolean = true,
    val alarmToneUri: Uri? = null,
    val themePreference: ThemePreference = ThemePreference.SYSTEM,
    val alarmMuted: Boolean = false,
)

class UserPreferencesRepository(private val context: Context) {
    val prefsFlow: Flow<UserPrefs> = context.dataStore.data.map { prefs: Preferences ->
        UserPrefs(
            playOnChargeStart = prefs[PrefKeys.PlayOnChargeStart] ?: true,
            playOnChargeStop = prefs[PrefKeys.PlayOnChargeStop] ?: false,
            vibrateOnAlarm = prefs[PrefKeys.VibrateOnAlarm] ?: true,
            alarmToneUri = prefs[PrefKeys.AlarmToneUri]?.let { Uri.parse(it) },
            themePreference = prefs[PrefKeys.Theme]?.let { runCatching { ThemePreference.valueOf(it) }.getOrNull() } ?: ThemePreference.SYSTEM,
            alarmMuted = prefs[PrefKeys.AlarmMuted] ?: false,
        )
    }

    suspend fun setPlayOnChargeStart(value: Boolean) {
        context.dataStore.edit { it[PrefKeys.PlayOnChargeStart] = value }
    }
    suspend fun setPlayOnChargeStop(value: Boolean) {
        context.dataStore.edit { it[PrefKeys.PlayOnChargeStop] = value }
    }
    suspend fun setVibrateOnAlarm(value: Boolean) {
        context.dataStore.edit { it[PrefKeys.VibrateOnAlarm] = value }
    }
    suspend fun setAlarmTone(uri: Uri?) {
        context.dataStore.edit {
            if (uri != null) it[PrefKeys.AlarmToneUri] = uri.toString() else it.remove(PrefKeys.AlarmToneUri)
        }
    }
    suspend fun setThemePreference(value: ThemePreference) {
        context.dataStore.edit { it[PrefKeys.Theme] = value.name }
    }
    suspend fun setAlarmMuted(value: Boolean) {
        context.dataStore.edit { it[PrefKeys.AlarmMuted] = value }
    }
}
