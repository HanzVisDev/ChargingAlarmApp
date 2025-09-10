package com.example.chargingalarm.service;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u0000 )2\u00020\u0001:\u0001)B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001f\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0002\u0010\u0010J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0012H\u0002J\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0012H\u0002J\b\u0010\u001b\u001a\u00020\u0012H\u0002J\b\u0010\u001c\u001a\u00020\u0012H\u0016J\b\u0010\u001d\u001a\u00020\u0012H\u0016J\"\u0010\u001e\u001a\u00020\u000e2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u000eH\u0016J\b\u0010!\u001a\u00020\u0012H\u0002J\u001a\u0010\"\u001a\u00020\u00122\b\u0010#\u001a\u0004\u0018\u00010\u00142\u0006\u0010$\u001a\u00020\u0004H\u0002J\b\u0010%\u001a\u00020\u0012H\u0002J!\u0010&\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0002\u0010\'J\b\u0010(\u001a\u00020\u0012H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lcom/example/chargingalarm/service/AlarmService;", "Landroid/app/Service;", "()V", "isForegroundStarted", "", "mediaPlayer", "Landroid/media/MediaPlayer;", "prefs", "Lcom/example/chargingalarm/datastore/UserPreferencesRepository;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "buildNotification", "Landroid/app/Notification;", "batteryPct", "", "isCharging", "(ILjava/lang/Boolean;)Landroid/app/Notification;", "createChannels", "", "defaultAlarm", "Landroid/net/Uri;", "mute", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onChargingConnected", "onChargingDisconnected", "onCreate", "onDestroy", "onStartCommand", "flags", "startId", "stopAlarm", "triggerAlarm", "alarmUri", "vibrate", "unmute", "updateNotification", "(ILjava/lang/Boolean;)V", "vibrateOnce", "Companion", "app_debug"})
public final class AlarmService extends android.app.Service {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope scope = null;
    private com.example.chargingalarm.datastore.UserPreferencesRepository prefs;
    @org.jetbrains.annotations.Nullable()
    private android.media.MediaPlayer mediaPlayer;
    private boolean isForegroundStarted = false;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_ID = "charging_alarm_channel";
    public static final int NOTIF_ID = 101;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_CHARGING_CONNECTED = "com.example.chargingalarm.ACTION_CHARGING_CONNECTED";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_CHARGING_DISCONNECTED = "com.example.chargingalarm.ACTION_CHARGING_DISCONNECTED";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_UPDATE_NOTIFICATION = "com.example.chargingalarm.ACTION_UPDATE_NOTIFICATION";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_MUTE = "com.example.chargingalarm.ACTION_MUTE";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_UNMUTE = "com.example.chargingalarm.ACTION_UNMUTE";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_BATTERY_PCT = "extra_battery_pct";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.chargingalarm.service.AlarmService.Companion Companion = null;
    
    public AlarmService() {
        super();
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    private final void onChargingConnected() {
    }
    
    private final void onChargingDisconnected() {
    }
    
    private final void triggerAlarm(android.net.Uri alarmUri, boolean vibrate) {
    }
    
    private final void stopAlarm() {
    }
    
    private final void vibrateOnce() {
    }
    
    private final void mute() {
    }
    
    private final void unmute() {
    }
    
    private final android.net.Uri defaultAlarm() {
        return null;
    }
    
    private final void createChannels() {
    }
    
    private final android.app.Notification buildNotification(int batteryPct, java.lang.Boolean isCharging) {
        return null;
    }
    
    private final void updateNotification(int batteryPct, java.lang.Boolean isCharging) {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/example/chargingalarm/service/AlarmService$Companion;", "", "()V", "ACTION_CHARGING_CONNECTED", "", "ACTION_CHARGING_DISCONNECTED", "ACTION_MUTE", "ACTION_UNMUTE", "ACTION_UPDATE_NOTIFICATION", "CHANNEL_ID", "EXTRA_BATTERY_PCT", "NOTIF_ID", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}