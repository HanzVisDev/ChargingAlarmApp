package com.example.chargingalarm.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u000e\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001cJ\u0010\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u001cH\u0002J\u0012\u0010#\u001a\u00020\u001e2\b\u0010$\u001a\u0004\u0018\u00010%H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00130\u00120\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000bR\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/example/chargingalarm/viewmodel/BatteryViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_batteryState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/chargingalarm/viewmodel/BatteryState;", "batteryState", "Lkotlinx/coroutines/flow/StateFlow;", "getBatteryState", "()Lkotlinx/coroutines/flow/StateFlow;", "context", "Landroid/content/Context;", "kotlin.jvm.PlatformType", "receiver", "Landroid/content/BroadcastReceiver;", "uiState", "Lkotlin/Pair;", "Lcom/example/chargingalarm/datastore/UserPrefs;", "getUiState", "userPrefsRepository", "Lcom/example/chargingalarm/datastore/UserPreferencesRepository;", "calculateTimeLeft", "", "batteryPct", "", "isCharging", "", "register", "", "setAlarmMuted", "muted", "setCharging", "value", "updateFromBatteryChanged", "intent", "Landroid/content/Intent;", "app_debug"})
public final class BatteryViewModel extends androidx.lifecycle.AndroidViewModel {
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.chargingalarm.datastore.UserPreferencesRepository userPrefsRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.chargingalarm.viewmodel.BatteryState> _batteryState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.chargingalarm.viewmodel.BatteryState> batteryState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<kotlin.Pair<com.example.chargingalarm.viewmodel.BatteryState, com.example.chargingalarm.datastore.UserPrefs>> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.BroadcastReceiver receiver = null;
    
    public BatteryViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.chargingalarm.viewmodel.BatteryState> getBatteryState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<kotlin.Pair<com.example.chargingalarm.viewmodel.BatteryState, com.example.chargingalarm.datastore.UserPrefs>> getUiState() {
        return null;
    }
    
    private final void register() {
    }
    
    private final void updateFromBatteryChanged(android.content.Intent intent) {
    }
    
    private final java.lang.String calculateTimeLeft(int batteryPct, boolean isCharging) {
        return null;
    }
    
    private final void setCharging(boolean value) {
    }
    
    public final void setAlarmMuted(boolean muted) {
    }
}