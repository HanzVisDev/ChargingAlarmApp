package com.example.chargingalarm.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018J&\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001cH\u0082@\u00a2\u0006\u0002\u0010\u001dJ\b\u0010\u001e\u001a\u00020\u0014H\u0002J\"\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020\u001c2\b\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020\u0018H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/example/chargingalarm/viewmodel/AlarmViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_alarmState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/chargingalarm/viewmodel/AlarmState;", "alarmState", "Lkotlinx/coroutines/flow/StateFlow;", "getAlarmState", "()Lkotlinx/coroutines/flow/StateFlow;", "context", "Landroid/content/Context;", "kotlin.jvm.PlatformType", "database", "Lcom/example/chargingalarm/data/AppDatabase;", "userPrefsRepository", "Lcom/example/chargingalarm/datastore/UserPreferencesRepository;", "checkAlarms", "", "batteryPct", "", "isCharging", "", "wasCharging", "logChargingEvent", "eventType", "", "(IZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observePreferences", "triggerAlarm", "alarmType", "toneUri", "Landroid/net/Uri;", "vibrate", "app_debug"})
public final class AlarmViewModel extends androidx.lifecycle.AndroidViewModel {
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.chargingalarm.datastore.UserPreferencesRepository userPrefsRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.chargingalarm.data.AppDatabase database = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.chargingalarm.viewmodel.AlarmState> _alarmState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.chargingalarm.viewmodel.AlarmState> alarmState = null;
    
    public AlarmViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.chargingalarm.viewmodel.AlarmState> getAlarmState() {
        return null;
    }
    
    private final void observePreferences() {
    }
    
    public final void checkAlarms(int batteryPct, boolean isCharging, boolean wasCharging) {
    }
    
    private final void triggerAlarm(java.lang.String alarmType, android.net.Uri toneUri, boolean vibrate) {
    }
    
    private final java.lang.Object logChargingEvent(int batteryPct, boolean isCharging, java.lang.String eventType, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}