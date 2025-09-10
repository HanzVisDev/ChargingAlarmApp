package com.example.chargingalarm.ui.screens

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chargingalarm.datastore.*
import com.example.chargingalarm.service.OverlayService
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    padding: PaddingValues,
    userPrefsRepository: UserPreferencesRepository
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val prefs by userPrefsRepository.prefsFlow.collectAsState(initial = null)
    
    val scrollState = rememberScrollState()
    
    Column(
        modifier = Modifier
            .padding(padding)
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Theme Settings (simplified)
        SettingsSection("Appearance") {
            SettingSwitch(
                title = "Dark Mode",
                checked = prefs?.themePreference == ThemePreference.DARK,
                onCheckedChange = { enabled ->
                    scope.launch {
                        userPrefsRepository.setThemePreference(
                            if (enabled) ThemePreference.DARK else ThemePreference.LIGHT
                        )
                    }
                }
            )
        }
        
        // Overlay Settings
        SettingsSection("Overlay") {
            SettingSwitch(
                title = "Enable Overlay",
                checked = prefs?.overlayEnabled ?: false,
                onCheckedChange = { enabled ->
                    scope.launch {
                        userPrefsRepository.setOverlayEnabled(enabled)
                        if (enabled && !OverlayService.hasOverlayPermission(context)) {
                            requestOverlayPermission(context)
                        }
                    }
                }
            )
            
            AnimationTemplateSelector(
                currentTemplate = prefs?.animationTemplate ?: AnimationTemplate.BUBBLE,
                onTemplateSelected = { template ->
                    scope.launch { userPrefsRepository.setAnimationTemplate(template) }
                }
            )
        }
        
        // Basic Alarm Settings
        SettingsSection("Basic Alarms") {
            SettingSwitch(
                title = "Play sound on charging start",
                checked = prefs?.playOnChargeStart ?: true,
                onCheckedChange = { scope.launch { userPrefsRepository.setPlayOnChargeStart(it) } }
            )
            
            SettingSwitch(
                title = "Play sound on charging stop",
                checked = prefs?.playOnChargeStop ?: false,
                onCheckedChange = { scope.launch { userPrefsRepository.setPlayOnChargeStop(it) } }
            )
            
            SettingSwitch(
                title = "Vibrate with alarm",
                checked = prefs?.vibrateOnAlarm ?: true,
                onCheckedChange = { scope.launch { userPrefsRepository.setVibrateOnAlarm(it) } }
            )
            
            AlarmToneSelector(
                title = "Default Alarm Tone",
                currentTone = prefs?.alarmToneUri,
                onToneSelected = { uri ->
                    scope.launch { userPrefsRepository.setAlarmTone(uri) }
                }
            )
        }
        
        // Advanced Alarm Settings
        SettingsSection("Advanced Alarms") {
            SettingSwitch(
                title = "Full Charge Alarm (100%)",
                checked = prefs?.fullChargeAlarmEnabled ?: false,
                onCheckedChange = { scope.launch { userPrefsRepository.setFullChargeAlarmEnabled(it) } }
            )
            
            AlarmToneSelector(
                title = "Full Charge Tone",
                currentTone = prefs?.fullChargeToneUri,
                onToneSelected = { uri ->
                    scope.launch { userPrefsRepository.setFullChargeTone(uri) }
                }
            )
            
            SettingSwitch(
                title = "Charge End Alarm",
                checked = prefs?.chargeEndAlarmEnabled ?: false,
                onCheckedChange = { scope.launch { userPrefsRepository.setChargeEndAlarmEnabled(it) } }
            )
            
            AlarmToneSelector(
                title = "Charge End Tone",
                currentTone = prefs?.chargeEndToneUri,
                onToneSelected = { uri ->
                    scope.launch { userPrefsRepository.setChargeEndTone(uri) }
                }
            )
            
            SettingSwitch(
                title = "Restricted Limit Alarm",
                checked = prefs?.restrictedLimitAlarmEnabled ?: false,
                onCheckedChange = { scope.launch { userPrefsRepository.setRestrictedLimitAlarmEnabled(it) } }
            )
            
            RestrictedLimitSelector(
                currentLimit = prefs?.restrictedLimitPercentage ?: 80,
                onLimitChanged = { limit ->
                    scope.launch { userPrefsRepository.setRestrictedLimitPercentage(limit) }
                }
            )
            
            AlarmToneSelector(
                title = "Restricted Limit Tone",
                currentTone = prefs?.restrictedLimitToneUri,
                onToneSelected = { uri ->
                    scope.launch { userPrefsRepository.setRestrictedLimitTone(uri) }
                }
            )
        }
    }
}

@Composable
fun SettingsSection(
    title: String,
    content: @Composable () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            content()
        }
    }
}

@Composable
fun SettingSwitch(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemePreferenceSelector(
    currentTheme: ThemePreference,
    onThemeSelected: (ThemePreference) -> Unit
) {
    Text(
        text = "Theme Preference",
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
    )
    
    Row(verticalAlignment = Alignment.CenterVertically) {
        FilterChip(
            selected = currentTheme == ThemePreference.LIGHT,
            onClick = { onThemeSelected(ThemePreference.LIGHT) },
            label = { Text("Light") }
        )
        Spacer(Modifier.width(4.dp))
        FilterChip(
            selected = currentTheme == ThemePreference.DARK,
            onClick = { onThemeSelected(ThemePreference.DARK) },
            label = { Text("Dark") }
        )
        Spacer(Modifier.width(4.dp))
        FilterChip(
            selected = currentTheme == ThemePreference.SYSTEM,
            onClick = { onThemeSelected(ThemePreference.SYSTEM) },
            label = { Text("System") }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimationTemplateSelector(
    currentTemplate: AnimationTemplate,
    onTemplateSelected: (AnimationTemplate) -> Unit
) {
    Text(
        text = "Animation Template",
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
    )
    
    Row(verticalAlignment = Alignment.CenterVertically) {
        FilterChip(
            selected = currentTemplate == AnimationTemplate.BUBBLE,
            onClick = { onTemplateSelected(AnimationTemplate.BUBBLE) },
            label = { Text("Bubble") }
        )
        Spacer(Modifier.width(4.dp))
        FilterChip(
            selected = currentTemplate == AnimationTemplate.NEON_WIRE,
            onClick = { onTemplateSelected(AnimationTemplate.NEON_WIRE) },
            label = { Text("Neon Wire") }
        )
        Spacer(Modifier.width(4.dp))
        FilterChip(
            selected = currentTemplate == AnimationTemplate.GRADIENT_PULSE,
            onClick = { onTemplateSelected(AnimationTemplate.GRADIENT_PULSE) },
            label = { Text("Gradient Pulse") }
        )
    }
}

@Composable
fun AlarmToneSelector(
    title: String,
    currentTone: Uri?,
    onToneSelected: (Uri?) -> Unit
) {
    val context = LocalContext.current
    val pickAudio = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        onToneSelected(uri)
    }
    
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
    )
    
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(
            text = currentTone?.toString() ?: "Default tone",
            modifier = Modifier.weight(1f),
            fontSize = 14.sp
        )
        Button(
            onClick = { pickAudio.launch("audio/*") },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text("Choose")
        }
    }
}

@Composable
fun RestrictedLimitSelector(
    currentLimit: Int,
    onLimitChanged: (Int) -> Unit
) {
    Text(
        text = "Restricted Limit Percentage",
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
    )
    
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(
            text = "$currentLimit%",
            modifier = Modifier.weight(1f),
            fontSize = 14.sp
        )
        
        Slider(
            value = currentLimit.toFloat(),
            onValueChange = { onLimitChanged(it.toInt()) },
            valueRange = 50f..100f,
            steps = 9, // 50, 55, 60, ..., 100
            modifier = Modifier.weight(2f)
        )
    }
}

private fun requestOverlayPermission(context: android.content.Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION).apply {
            data = Uri.parse("package:${context.packageName}")
        }
        context.startActivity(intent)
    }
}
