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
    
    // New preferences
    val OverlayEnabled = booleanPreferencesKey("overlay_enabled")
    val AnimationTemplate = stringPreferencesKey("animation_template")
    val FullChargeAlarmEnabled = booleanPreferencesKey("full_charge_alarm_enabled")
    val ChargeEndAlarmEnabled = booleanPreferencesKey("charge_end_alarm_enabled")
    val RestrictedLimitAlarmEnabled = booleanPreferencesKey("restricted_limit_alarm_enabled")
    val RestrictedLimitPercentage = stringPreferencesKey("restricted_limit_percentage")
    val FullChargeToneUri = stringPreferencesKey("full_charge_tone_uri")
    val ChargeEndToneUri = stringPreferencesKey("charge_end_tone_uri")
    val RestrictedLimitToneUri = stringPreferencesKey("restricted_limit_tone_uri")
}

enum class AnimationTemplate { BUBBLE, NEON_WIRE, GRADIENT_PULSE }

data class UserPrefs(
    val playOnChargeStart: Boolean = true,
    val playOnChargeStop: Boolean = false,
    val vibrateOnAlarm: Boolean = true,
    val alarmToneUri: Uri? = null,
    val themePreference: ThemePreference = ThemePreference.SYSTEM,
    val alarmMuted: Boolean = false,
    
    // New preferences
    val overlayEnabled: Boolean = false,
    val animationTemplate: AnimationTemplate = AnimationTemplate.BUBBLE,
    val fullChargeAlarmEnabled: Boolean = false,
    val chargeEndAlarmEnabled: Boolean = false,
    val restrictedLimitAlarmEnabled: Boolean = false,
    val restrictedLimitPercentage: Int = 80,
    val fullChargeToneUri: Uri? = null,
    val chargeEndToneUri: Uri? = null,
    val restrictedLimitToneUri: Uri? = null,
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
            
            // New preferences
            overlayEnabled = prefs[PrefKeys.OverlayEnabled] ?: false,
            animationTemplate = prefs[PrefKeys.AnimationTemplate]?.let { runCatching { AnimationTemplate.valueOf(it) }.getOrNull() } ?: AnimationTemplate.BUBBLE,
            fullChargeAlarmEnabled = prefs[PrefKeys.FullChargeAlarmEnabled] ?: false,
            chargeEndAlarmEnabled = prefs[PrefKeys.ChargeEndAlarmEnabled] ?: false,
            restrictedLimitAlarmEnabled = prefs[PrefKeys.RestrictedLimitAlarmEnabled] ?: false,
            restrictedLimitPercentage = prefs[PrefKeys.RestrictedLimitPercentage]?.toIntOrNull() ?: 80,
            fullChargeToneUri = prefs[PrefKeys.FullChargeToneUri]?.let { Uri.parse(it) },
            chargeEndToneUri = prefs[PrefKeys.ChargeEndToneUri]?.let { Uri.parse(it) },
            restrictedLimitToneUri = prefs[PrefKeys.RestrictedLimitToneUri]?.let { Uri.parse(it) },
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
    
    // New preference setters
    suspend fun setOverlayEnabled(value: Boolean) {
        context.dataStore.edit { it[PrefKeys.OverlayEnabled] = value }
    }
    
    suspend fun setAnimationTemplate(value: AnimationTemplate) {
        context.dataStore.edit { it[PrefKeys.AnimationTemplate] = value.name }
    }
    
    suspend fun setFullChargeAlarmEnabled(value: Boolean) {
        context.dataStore.edit { it[PrefKeys.FullChargeAlarmEnabled] = value }
    }
    
    suspend fun setChargeEndAlarmEnabled(value: Boolean) {
        context.dataStore.edit { it[PrefKeys.ChargeEndAlarmEnabled] = value }
    }
    
    suspend fun setRestrictedLimitAlarmEnabled(value: Boolean) {
        context.dataStore.edit { it[PrefKeys.RestrictedLimitAlarmEnabled] = value }
    }
    
    suspend fun setRestrictedLimitPercentage(value: Int) {
        context.dataStore.edit { it[PrefKeys.RestrictedLimitPercentage] = value.toString() }
    }
    
    suspend fun setFullChargeTone(uri: Uri?) {
        context.dataStore.edit {
            if (uri != null) it[PrefKeys.FullChargeToneUri] = uri.toString() else it.remove(PrefKeys.FullChargeToneUri)
        }
    }
    
    suspend fun setChargeEndTone(uri: Uri?) {
        context.dataStore.edit {
            if (uri != null) it[PrefKeys.ChargeEndToneUri] = uri.toString() else it.remove(PrefKeys.ChargeEndToneUri)
        }
    }
    
    suspend fun setRestrictedLimitTone(uri: Uri?) {
        context.dataStore.edit {
            if (uri != null) it[PrefKeys.RestrictedLimitToneUri] = uri.toString() else it.remove(PrefKeys.RestrictedLimitToneUri)
        }
    }
}
