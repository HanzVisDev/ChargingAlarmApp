package com.example.chargingalarm.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\\\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0014\u0010\u0006\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u001a$\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u001a$\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u001a,\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u001a\u0018\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0007\u001a#\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\u00010\u001b\u00a2\u0006\u0002\b\u001cH\u0007\u001a$\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u001f2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u001a\u0010\u0010!\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#H\u0002\u00a8\u0006$"}, d2 = {"AlarmToneSelector", "", "title", "", "currentTone", "Landroid/net/Uri;", "onToneSelected", "Lkotlin/Function1;", "AnimationTemplateSelector", "currentTemplate", "Lcom/example/chargingalarm/datastore/AnimationTemplate;", "onTemplateSelected", "RestrictedLimitSelector", "currentLimit", "", "onLimitChanged", "SettingSwitch", "checked", "", "onCheckedChange", "SettingsScreen", "padding", "Landroidx/compose/foundation/layout/PaddingValues;", "userPrefsRepository", "Lcom/example/chargingalarm/datastore/UserPreferencesRepository;", "SettingsSection", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "ThemePreferenceSelector", "currentTheme", "Lcom/example/chargingalarm/datastore/ThemePreference;", "onThemeSelected", "requestOverlayPermission", "context", "Landroid/content/Context;", "app_debug"})
public final class SettingsScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void SettingsScreen(@org.jetbrains.annotations.NotNull()
    androidx.compose.foundation.layout.PaddingValues padding, @org.jetbrains.annotations.NotNull()
    com.example.chargingalarm.datastore.UserPreferencesRepository userPrefsRepository) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SettingsSection(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> content) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SettingSwitch(@org.jetbrains.annotations.NotNull()
    java.lang.String title, boolean checked, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onCheckedChange) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void ThemePreferenceSelector(@org.jetbrains.annotations.NotNull()
    com.example.chargingalarm.datastore.ThemePreference currentTheme, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.example.chargingalarm.datastore.ThemePreference, kotlin.Unit> onThemeSelected) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void AnimationTemplateSelector(@org.jetbrains.annotations.NotNull()
    com.example.chargingalarm.datastore.AnimationTemplate currentTemplate, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.example.chargingalarm.datastore.AnimationTemplate, kotlin.Unit> onTemplateSelected) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void AlarmToneSelector(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.Nullable()
    android.net.Uri currentTone, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super android.net.Uri, kotlin.Unit> onToneSelected) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void RestrictedLimitSelector(int currentLimit, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onLimitChanged) {
    }
    
    private static final void requestOverlayPermission(android.content.Context context) {
    }
}