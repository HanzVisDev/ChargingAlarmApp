package com.example.chargingalarm.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chargingalarm.data.ChargingHistory
import com.example.chargingalarm.data.AppDatabase
import com.example.chargingalarm.datastore.UserPreferencesRepository
import com.example.chargingalarm.service.OverlayService
import com.example.chargingalarm.viewmodel.AlarmViewModel
import com.example.chargingalarm.viewmodel.BatteryViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DashboardScreen(
    padding: PaddingValues,
    userPrefsRepository: UserPreferencesRepository
) {
    val batteryViewModel: BatteryViewModel = viewModel()
    val alarmViewModel: AlarmViewModel = viewModel()
    val batteryState by batteryViewModel.batteryState.collectAsState()
    val alarmState by alarmViewModel.alarmState.collectAsState()
    
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val database = remember { AppDatabase.getDatabase(context) }
    
    var recentHistory by remember { mutableStateOf<List<ChargingHistory>>(emptyList()) }
    
    LaunchedEffect(Unit) {
        database.chargingHistoryDao().getRecentHistory(5).collect { history ->
            recentHistory = history
        }
    }
    
    LazyColumn(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            BatteryBubbleCard(batteryState = batteryState)
        }
        
        item {
            BatteryInfoCard(batteryState = batteryState)
        }
        
        item {
            QuickActionsCard(
                alarmState = alarmState,
                onToggleOverlay = { enabled ->
                    scope.launch {
                        userPrefsRepository.setOverlayEnabled(enabled)
                        if (enabled) {
                            val intent = android.content.Intent(context, OverlayService::class.java).apply {
                                action = OverlayService.ACTION_SHOW_OVERLAY
                            }
                            context.startService(intent)
                        } else {
                            val intent = android.content.Intent(context, OverlayService::class.java).apply {
                                action = OverlayService.ACTION_HIDE_OVERLAY
                            }
                            context.startService(intent)
                        }
                    }
                }
            )
        }
        
        item {
            RecentActivityCard(recentHistory = recentHistory)
        }
        
        item {
            FooterBranding()
        }
    }
}

@Composable
fun BatteryBubbleCard(batteryState: com.example.chargingalarm.viewmodel.BatteryState) {
    val sweep by animateFloatAsState(
        targetValue = batteryState.batteryPct / 100f,
        animationSpec = tween(1000),
        label = "battery_sweep"
    )
    
    val chargingGlow by animateFloatAsState(
        targetValue = if (batteryState.isCharging) 1f else 0f,
        animationSpec = tween(500),
        label = "charging_glow"
    )
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.size(220.dp),
                contentAlignment = Alignment.Center
            ) {
                // Background circle
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawArc(
                        color = Color.LightGray,
                        startAngle = -90f,
                        sweepAngle = 360f,
                        useCenter = false,
                        style = Stroke(width = 24f, cap = StrokeCap.Round)
                    )
                }
                
                // Battery level arc
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val batteryColor = if (batteryState.isCharging) {
                        Color(0xFF1E88E5).copy(alpha = 0.8f + chargingGlow * 0.2f)
                    } else {
                        Color(0xFFE53935)
                    }
                    
                    drawArc(
                        color = batteryColor,
                        startAngle = -90f,
                        sweepAngle = 360f * sweep,
                        useCenter = false,
                        style = Stroke(width = 24f, cap = StrokeCap.Round)
                    )
                }
                
                // Charging animation overlay
                if (batteryState.isCharging) {
                    ChargingAnimationOverlay(chargingGlow = chargingGlow)
                }
            }
            
            Text(
                text = "${batteryState.batteryPct}%",
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )
            
            Text(
                text = if (batteryState.isCharging) "Charging" else "Not Charging",
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun ChargingAnimationOverlay(chargingGlow: Float) {
    val infiniteTransition = rememberInfiniteTransition(label = "charging")
    val pulse by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )
    
    Canvas(modifier = Modifier.fillMaxSize()) {
        val centerX = size.width / 2
        val centerY = size.height / 2
        val radius = 100f * pulse * chargingGlow
        
        drawCircle(
            color = Color.Blue.copy(alpha = 0.3f * chargingGlow),
            radius = radius,
            center = androidx.compose.ui.geometry.Offset(centerX, centerY)
        )
    }
}

@Composable
fun BatteryInfoCard(batteryState: com.example.chargingalarm.viewmodel.BatteryState) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BatteryInfoItem(
                icon = Icons.Filled.Schedule,
                label = "Time Left",
                value = batteryState.timeLeft
            )
            
            BatteryInfoItem(
                icon = Icons.Filled.Power,
                label = "Power",
                value = "${batteryState.powerLevel}W"
            )
            
            BatteryInfoItem(
                icon = Icons.Filled.Thermostat,
                label = "Temperature",
                value = "${batteryState.temperature.toInt()}°C"
            )
        }
    }
}

@Composable
fun BatteryInfoItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun QuickActionsCard(
    alarmState: com.example.chargingalarm.viewmodel.AlarmState,
    onToggleOverlay: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Quick Actions",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                QuickActionButton(
                    icon = Icons.Filled.BatterySaver,
                    label = "Set Max Charge",
                    onClick = { /* TODO: Implement */ }
                )
                
                QuickActionButton(
                    icon = Icons.Filled.Visibility,
                    label = "Toggle Overlay",
                    onClick = { onToggleOverlay(true) }
                )
                
                QuickActionButton(
                    icon = Icons.Filled.Alarm,
                    label = "Alarm Settings",
                    subtitle = "${alarmState.activeAlarmsCount} active",
                    onClick = { /* TODO: Navigate to settings */ }
                )
                
                QuickActionButton(
                    icon = Icons.Filled.Analytics,
                    label = "Battery Stats",
                    onClick = { /* TODO: Navigate to history */ }
                )
            }
        }
    }
}

@Composable
fun QuickActionButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    subtitle: String? = null,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(32.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = label,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp)
        )
        subtitle?.let {
            Text(
                text = it,
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun RecentActivityCard(recentHistory: List<ChargingHistory>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Recent Activity",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            
            if (recentHistory.isEmpty()) {
                Text(
                    text = "No recent charging events",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            } else {
                recentHistory.forEach { event ->
                    RecentActivityItem(event = event)
                }
            }
        }
    }
}

@Composable
fun RecentActivityItem(event: ChargingHistory) {
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    val dateFormat = SimpleDateFormat("MMM dd", Locale.getDefault())
    val date = Date(event.timestamp)
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = when (event.eventType) {
                "CONNECTED" -> Icons.Filled.Power
                "DISCONNECTED" -> Icons.Filled.PowerOff
                "FULL_CHARGE" -> Icons.Filled.BatteryFull
                else -> Icons.Filled.BatteryStd
            },
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        
        Column(
            modifier = Modifier.padding(start = 12.dp)
        ) {
            Text(
                text = "${event.batteryLevel}% - ${event.eventType}",
                fontSize = 14.sp
            )
            Text(
                text = "${dateFormat.format(date)} ${timeFormat.format(date)}",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun FooterBranding() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Small icon placeholder - you can replace with actual icon from URL
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        
        Text(
            text = "Developed by –",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}
