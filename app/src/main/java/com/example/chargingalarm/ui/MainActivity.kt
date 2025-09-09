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
import com.example.chargingalarm.ui.screens.DashboardScreen
import com.example.chargingalarm.ui.screens.SettingsScreen
import com.example.chargingalarm.viewmodel.BatteryViewModel
import com.example.chargingalarm.data.AppDatabase
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
            val database = remember { AppDatabase.getDatabase(context) }
            val prefs by repo.prefsFlow.collectAsState(initial = null)
            ChargingAlarmTheme(prefs?.themePreference ?: ThemePreference.SYSTEM) {
                Surface(Modifier.fillMaxSize()) {
                    MainScreen(repo, database)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(repo: UserPreferencesRepository, database: AppDatabase) {
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
            0 -> DashboardScreen(padding, repo)
            else -> SettingsScreen(padding, repo)
        }
    }
}

