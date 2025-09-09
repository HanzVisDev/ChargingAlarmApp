package com.example.chargingalarm.ui

import android.Manifest
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryFull
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chargingalarm.datastore.ThemePreference
import com.example.chargingalarm.datastore.UserPreferencesRepository
import com.example.chargingalarm.ui.theme.ChargingAlarmTheme
import com.example.chargingalarm.viewmodel.BatteryViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel: BatteryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {}.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
        setContent {
            val context = LocalContext.current
            val repo = remember { UserPreferencesRepository(context) }
            val prefs by repo.prefsFlow.collectAsState(initial = null)
            ChargingAlarmTheme(prefs?.themePreference ?: ThemePreference.SYSTEM) {
                Surface(Modifier.fillMaxSize()) {
                    MainScreen(repo)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(repo: UserPreferencesRepository) {
    var page by remember { mutableStateOf(0) }
    Scaffold(
        topBar = { TopAppBar(title = { Text("Charging Alarm") }) },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(selected = page == 0, onClick = { page = 0 }, icon = { Icon(Icons.Filled.BatteryFull, contentDescription = null) }, label = { Text("Dashboard") })
                NavigationBarItem(selected = page == 1, onClick = { page = 1 }, icon = { Icon(Icons.Filled.Settings, contentDescription = null) }, label = { Text("Settings") })
            }
        }
    ) { padding ->
        when (page) {
            0 -> Dashboard(padding)
            else -> SettingsScreen(padding, repo)
        }
    }
}

@Composable
fun Dashboard(padding: PaddingValues) {
    val vm: BatteryViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    val state by vm.batteryState.collectAsState()
    val sweep by animateFloatAsState(targetValue = state.batteryPct / 100f)

    Column(
        modifier = Modifier
            .padding(padding)
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(modifier = Modifier.size(220.dp)) {
            drawArc(
                color = Color.LightGray,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = 24f, cap = StrokeCap.Round)
            )
            drawArc(
                color = if (state.isCharging) Color(0xFF1E88E5) else Color(0xFFE53935),
                startAngle = -90f,
                sweepAngle = 360f * sweep,
                useCenter = false,
                style = Stroke(width = 24f, cap = StrokeCap.Round)
            )
        }
        Text("${state.batteryPct}%", fontSize = 42.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 16.dp))
        Text(if (state.isCharging) "Charging" else "Not Charging", fontSize = 18.sp, modifier = Modifier.padding(top = 8.dp))
    }
}

@Composable
fun SettingsScreen(padding: PaddingValues, repo: UserPreferencesRepository) {
    val prefs by repo.prefsFlow.collectAsState(initial = null)
    val scope = rememberCoroutineScope()
    Column(Modifier.padding(padding).padding(16.dp)) {
        Text("Preferences", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        SettingSwitch("Play sound on charging start", prefs?.playOnChargeStart ?: true) { scope.launch { repo.setPlayOnChargeStart(it) } }
        SettingSwitch("Play sound on charging stop", prefs?.playOnChargeStop ?: false) { scope.launch { repo.setPlayOnChargeStop(it) } }
        SettingSwitch("Vibrate with alarm", prefs?.vibrateOnAlarm ?: true) { scope.launch { repo.setVibrateOnAlarm(it) } }

        Spacer(Modifier.height(8.dp))
        Text("Alarm tone", style = MaterialTheme.typography.titleMedium)
        val pickAudio = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            scope.launch { repo.setAlarmTone(uri) }
        }
        Button(onClick = { pickAudio.launch("audio/*") }) { Text("Choose tone") }

        Spacer(Modifier.height(8.dp))
        Text("Dark Mode", style = MaterialTheme.typography.titleMedium)
        SegmentedButtonRow(prefs?.themePreference ?: ThemePreference.SYSTEM) { newTheme ->
            scope.launch { repo.setThemePreference(newTheme) }
        }
    }
}

@Composable
fun SettingSwitch(title: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 8.dp)) {
        Text(title, modifier = Modifier.weight(1f))
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SegmentedButtonRow(theme: ThemePreference, onSelect: (ThemePreference) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        FilterChip(selected = theme == ThemePreference.LIGHT, onClick = { onSelect(ThemePreference.LIGHT) }, label = { Text("Light") })
        Spacer(Modifier.width(4.dp))
        FilterChip(selected = theme == ThemePreference.DARK, onClick = { onSelect(ThemePreference.DARK) }, label = { Text("Dark") })
        Spacer(Modifier.width(4.dp))
        FilterChip(selected = theme == ThemePreference.SYSTEM, onClick = { onSelect(ThemePreference.SYSTEM) }, label = { Text("System") })
    }
}
