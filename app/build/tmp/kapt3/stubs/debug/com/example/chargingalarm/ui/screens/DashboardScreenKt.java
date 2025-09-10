package com.example.chargingalarm.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000Z\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a \u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0007\u001a\u0010\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\u0007\u001a\u0018\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007\u001a\b\u0010\u0013\u001a\u00020\u0001H\u0007\u001a2\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\t2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\u0017H\u0007\u001a$\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u001a2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00010\u001cH\u0007\u001a\u0016\u0010\u001e\u001a\u00020\u00012\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 H\u0007\u001a\u0010\u0010\"\u001a\u00020\u00012\u0006\u0010#\u001a\u00020!H\u0007\u00a8\u0006$"}, d2 = {"BatteryBubbleCard", "", "batteryState", "Lcom/example/chargingalarm/viewmodel/BatteryState;", "BatteryInfoCard", "BatteryInfoItem", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "label", "", "value", "ChargingAnimationOverlay", "chargingGlow", "", "DashboardScreen", "padding", "Landroidx/compose/foundation/layout/PaddingValues;", "userPrefsRepository", "Lcom/example/chargingalarm/datastore/UserPreferencesRepository;", "FooterBranding", "QuickActionButton", "subtitle", "onClick", "Lkotlin/Function0;", "QuickActionsCard", "alarmState", "Lcom/example/chargingalarm/viewmodel/AlarmState;", "onToggleOverlay", "Lkotlin/Function1;", "", "RecentActivityCard", "recentHistory", "", "Lcom/example/chargingalarm/data/ChargingHistory;", "RecentActivityItem", "event", "app_debug"})
public final class DashboardScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void DashboardScreen(@org.jetbrains.annotations.NotNull()
    androidx.compose.foundation.layout.PaddingValues padding, @org.jetbrains.annotations.NotNull()
    com.example.chargingalarm.datastore.UserPreferencesRepository userPrefsRepository) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void BatteryBubbleCard(@org.jetbrains.annotations.NotNull()
    com.example.chargingalarm.viewmodel.BatteryState batteryState) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ChargingAnimationOverlay(float chargingGlow) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void BatteryInfoCard(@org.jetbrains.annotations.NotNull()
    com.example.chargingalarm.viewmodel.BatteryState batteryState) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void BatteryInfoItem(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.graphics.vector.ImageVector icon, @org.jetbrains.annotations.NotNull()
    java.lang.String label, @org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void QuickActionsCard(@org.jetbrains.annotations.NotNull()
    com.example.chargingalarm.viewmodel.AlarmState alarmState, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onToggleOverlay) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void QuickActionButton(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.graphics.vector.ImageVector icon, @org.jetbrains.annotations.NotNull()
    java.lang.String label, @org.jetbrains.annotations.Nullable()
    java.lang.String subtitle, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void RecentActivityCard(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.chargingalarm.data.ChargingHistory> recentHistory) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void RecentActivityItem(@org.jetbrains.annotations.NotNull()
    com.example.chargingalarm.data.ChargingHistory event) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void FooterBranding() {
    }
}