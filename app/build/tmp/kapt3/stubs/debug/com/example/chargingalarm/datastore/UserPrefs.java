package com.example.chargingalarm.datastore;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b,\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u00a3\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\u0016J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\t\u0010.\u001a\u00020\u0012H\u00c6\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\t\u00102\u001a\u00020\u0003H\u00c6\u0003J\t\u00103\u001a\u00020\u0003H\u00c6\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\t\u00105\u001a\u00020\tH\u00c6\u0003J\t\u00106\u001a\u00020\u0003H\u00c6\u0003J\t\u00107\u001a\u00020\u0003H\u00c6\u0003J\t\u00108\u001a\u00020\rH\u00c6\u0003J\t\u00109\u001a\u00020\u0003H\u00c6\u0003J\u00a7\u0001\u0010:\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001J\u0013\u0010;\u001a\u00020\u00032\b\u0010<\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010=\u001a\u00020\u0012H\u00d6\u0001J\t\u0010>\u001a\u00020?H\u00d6\u0001R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0018R\u0011\u0010\u0010\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001aR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0018\u00a8\u0006@"}, d2 = {"Lcom/example/chargingalarm/datastore/UserPrefs;", "", "playOnChargeStart", "", "playOnChargeStop", "vibrateOnAlarm", "alarmToneUri", "Landroid/net/Uri;", "themePreference", "Lcom/example/chargingalarm/datastore/ThemePreference;", "alarmMuted", "overlayEnabled", "animationTemplate", "Lcom/example/chargingalarm/datastore/AnimationTemplate;", "fullChargeAlarmEnabled", "chargeEndAlarmEnabled", "restrictedLimitAlarmEnabled", "restrictedLimitPercentage", "", "fullChargeToneUri", "chargeEndToneUri", "restrictedLimitToneUri", "(ZZZLandroid/net/Uri;Lcom/example/chargingalarm/datastore/ThemePreference;ZZLcom/example/chargingalarm/datastore/AnimationTemplate;ZZZILandroid/net/Uri;Landroid/net/Uri;Landroid/net/Uri;)V", "getAlarmMuted", "()Z", "getAlarmToneUri", "()Landroid/net/Uri;", "getAnimationTemplate", "()Lcom/example/chargingalarm/datastore/AnimationTemplate;", "getChargeEndAlarmEnabled", "getChargeEndToneUri", "getFullChargeAlarmEnabled", "getFullChargeToneUri", "getOverlayEnabled", "getPlayOnChargeStart", "getPlayOnChargeStop", "getRestrictedLimitAlarmEnabled", "getRestrictedLimitPercentage", "()I", "getRestrictedLimitToneUri", "getThemePreference", "()Lcom/example/chargingalarm/datastore/ThemePreference;", "getVibrateOnAlarm", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "", "app_debug"})
public final class UserPrefs {
    private final boolean playOnChargeStart = false;
    private final boolean playOnChargeStop = false;
    private final boolean vibrateOnAlarm = false;
    @org.jetbrains.annotations.Nullable()
    private final android.net.Uri alarmToneUri = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.chargingalarm.datastore.ThemePreference themePreference = null;
    private final boolean alarmMuted = false;
    private final boolean overlayEnabled = false;
    @org.jetbrains.annotations.NotNull()
    private final com.example.chargingalarm.datastore.AnimationTemplate animationTemplate = null;
    private final boolean fullChargeAlarmEnabled = false;
    private final boolean chargeEndAlarmEnabled = false;
    private final boolean restrictedLimitAlarmEnabled = false;
    private final int restrictedLimitPercentage = 0;
    @org.jetbrains.annotations.Nullable()
    private final android.net.Uri fullChargeToneUri = null;
    @org.jetbrains.annotations.Nullable()
    private final android.net.Uri chargeEndToneUri = null;
    @org.jetbrains.annotations.Nullable()
    private final android.net.Uri restrictedLimitToneUri = null;
    
    public UserPrefs(boolean playOnChargeStart, boolean playOnChargeStop, boolean vibrateOnAlarm, @org.jetbrains.annotations.Nullable()
    android.net.Uri alarmToneUri, @org.jetbrains.annotations.NotNull()
    com.example.chargingalarm.datastore.ThemePreference themePreference, boolean alarmMuted, boolean overlayEnabled, @org.jetbrains.annotations.NotNull()
    com.example.chargingalarm.datastore.AnimationTemplate animationTemplate, boolean fullChargeAlarmEnabled, boolean chargeEndAlarmEnabled, boolean restrictedLimitAlarmEnabled, int restrictedLimitPercentage, @org.jetbrains.annotations.Nullable()
    android.net.Uri fullChargeToneUri, @org.jetbrains.annotations.Nullable()
    android.net.Uri chargeEndToneUri, @org.jetbrains.annotations.Nullable()
    android.net.Uri restrictedLimitToneUri) {
        super();
    }
    
    public final boolean getPlayOnChargeStart() {
        return false;
    }
    
    public final boolean getPlayOnChargeStop() {
        return false;
    }
    
    public final boolean getVibrateOnAlarm() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.net.Uri getAlarmToneUri() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.chargingalarm.datastore.ThemePreference getThemePreference() {
        return null;
    }
    
    public final boolean getAlarmMuted() {
        return false;
    }
    
    public final boolean getOverlayEnabled() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.chargingalarm.datastore.AnimationTemplate getAnimationTemplate() {
        return null;
    }
    
    public final boolean getFullChargeAlarmEnabled() {
        return false;
    }
    
    public final boolean getChargeEndAlarmEnabled() {
        return false;
    }
    
    public final boolean getRestrictedLimitAlarmEnabled() {
        return false;
    }
    
    public final int getRestrictedLimitPercentage() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.net.Uri getFullChargeToneUri() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.net.Uri getChargeEndToneUri() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.net.Uri getRestrictedLimitToneUri() {
        return null;
    }
    
    public UserPrefs() {
        super();
    }
    
    public final boolean component1() {
        return false;
    }
    
    public final boolean component10() {
        return false;
    }
    
    public final boolean component11() {
        return false;
    }
    
    public final int component12() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.net.Uri component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.net.Uri component14() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.net.Uri component15() {
        return null;
    }
    
    public final boolean component2() {
        return false;
    }
    
    public final boolean component3() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.net.Uri component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.chargingalarm.datastore.ThemePreference component5() {
        return null;
    }
    
    public final boolean component6() {
        return false;
    }
    
    public final boolean component7() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.chargingalarm.datastore.AnimationTemplate component8() {
        return null;
    }
    
    public final boolean component9() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.chargingalarm.datastore.UserPrefs copy(boolean playOnChargeStart, boolean playOnChargeStop, boolean vibrateOnAlarm, @org.jetbrains.annotations.Nullable()
    android.net.Uri alarmToneUri, @org.jetbrains.annotations.NotNull()
    com.example.chargingalarm.datastore.ThemePreference themePreference, boolean alarmMuted, boolean overlayEnabled, @org.jetbrains.annotations.NotNull()
    com.example.chargingalarm.datastore.AnimationTemplate animationTemplate, boolean fullChargeAlarmEnabled, boolean chargeEndAlarmEnabled, boolean restrictedLimitAlarmEnabled, int restrictedLimitPercentage, @org.jetbrains.annotations.Nullable()
    android.net.Uri fullChargeToneUri, @org.jetbrains.annotations.Nullable()
    android.net.Uri chargeEndToneUri, @org.jetbrains.annotations.Nullable()
    android.net.Uri restrictedLimitToneUri) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}