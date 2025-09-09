package com.example.chargingalarm.ui.overlay

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chargingalarm.datastore.AnimationTemplate
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ChargingOverlay(
    animationTemplate: AnimationTemplate,
    onDismiss: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.3f))
            .clickable { onDismiss() },
        contentAlignment = Alignment.Center
    ) {
        when (animationTemplate) {
            AnimationTemplate.BUBBLE -> BubbleAnimation()
            AnimationTemplate.NEON_WIRE -> NeonWireAnimation()
            AnimationTemplate.GRADIENT_PULSE -> GradientPulseAnimation()
        }
    }
}

@Composable
fun BubbleAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "bubble")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )
    
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )
    
    Canvas(
        modifier = Modifier.size(200.dp)
    ) {
        val centerX = size.width / 2
        val centerY = size.height / 2
        val radius = 80f * scale
        
        // Outer glow
        drawCircle(
            color = Color.Blue.copy(alpha = alpha * 0.3f),
            radius = radius + 20f,
            center = androidx.compose.ui.geometry.Offset(centerX, centerY)
        )
        
        // Main bubble
        drawCircle(
            color = Color.Blue.copy(alpha = alpha),
            radius = radius,
            center = androidx.compose.ui.geometry.Offset(centerX, centerY)
        )
        
        // Inner highlight
        drawCircle(
            color = Color.White.copy(alpha = alpha * 0.5f),
            radius = radius * 0.3f,
            center = androidx.compose.ui.geometry.Offset(centerX * 0.7f, centerY * 0.7f)
        )
    }
}

@Composable
fun NeonWireAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "neon")
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000),
            repeatMode = RepeatMode.Restart
        ),
        label = "progress"
    )
    
    Canvas(
        modifier = Modifier.size(300.dp, 100.dp)
    ) {
        val path = Path().apply {
            moveTo(50f, size.height / 2)
            lineTo(size.width - 50f, size.height / 2)
        }
        
        // Glow effect
        drawPath(
            path = path,
            color = Color.Cyan.copy(alpha = 0.8f),
            style = Stroke(width = 8f, cap = StrokeCap.Round)
        )
        
        // Main wire
        drawPath(
            path = path,
            color = Color.Cyan,
            style = Stroke(width = 4f, cap = StrokeCap.Round)
        )
        
        // Moving charge particle
        val particleX = 50f + (size.width - 100f) * progress
        drawCircle(
            color = Color.White,
            radius = 6f,
            center = androidx.compose.ui.geometry.Offset(particleX, size.height / 2)
        )
    }
}

@Composable
fun GradientPulseAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )
    
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )
    
    Canvas(
        modifier = Modifier.size(250.dp, 60.dp)
    ) {
        val centerY = size.height / 2
        val barWidth = size.width / 5
        val barHeight = 40f * scale
        
        // Draw gradient bars
        for (i in 0..4) {
            val x = i * barWidth + barWidth / 2
            val gradientAlpha = alpha * (1f - i * 0.1f)
            
            drawRect(
                color = Color.Green.copy(alpha = gradientAlpha),
                topLeft = androidx.compose.ui.geometry.Offset(x - barWidth / 2, centerY - barHeight / 2),
                size = androidx.compose.ui.geometry.Size(barWidth - 10f, barHeight)
            )
        }
    }
}
