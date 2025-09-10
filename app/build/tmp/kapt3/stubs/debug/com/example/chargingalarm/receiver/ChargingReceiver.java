package com.example.chargingalarm.receiver;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0018\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J)\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002\u00a2\u0006\u0002\u0010\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/example/chargingalarm/receiver/ChargingReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "scope", "Lkotlinx/coroutines/CoroutineScope;", "logChargingEvent", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "eventType", "", "onReceive", "startService", "action", "batteryPct", "", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Integer;)V", "app_debug"})
public final class ChargingReceiver extends android.content.BroadcastReceiver {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope scope = null;
    
    public ChargingReceiver() {
        super();
    }
    
    @java.lang.Override()
    public void onReceive(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
    }
    
    private final void startService(android.content.Context context, java.lang.String action, java.lang.Integer batteryPct) {
    }
    
    private final void logChargingEvent(android.content.Context context, android.content.Intent intent, java.lang.String eventType) {
    }
}